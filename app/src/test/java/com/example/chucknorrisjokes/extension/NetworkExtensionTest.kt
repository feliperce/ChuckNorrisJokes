package com.example.chucknorrisjokes.extension

import com.example.chucknorrisjokes.data.remote.service.MockService
import com.example.chucknorrisjokes.data.statushandler.Status
import com.example.chucknorrisjokes.di.respositoryModule
import com.example.chucknorrisjokes.di.retrofitModule
import com.example.chucknorrisjokes.di.viewModelModule
import com.example.chucknorrisjokes.utils.MockRetrofitBuilder
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import retrofit2.Retrofit
import java.net.HttpURLConnection

class NetworkExtensionTest : AutoCloseKoinTest() {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var retrofit: Retrofit
    private lateinit var fakeService: MockService

    @Before
    fun setUp() {
        val koin = startKoin {
            modules(arrayListOf(
                retrofitModule,
                respositoryModule,
                viewModelModule
            ))
        }


        mockWebServer = MockWebServer()
        retrofit = MockRetrofitBuilder.build(mockWebServer.url("/"))
        fakeService = MockRetrofitBuilder.buildMockService(retrofit)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun networkCall_success_returnSuccess() {
        val mockResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody("{}")
        mockWebServer.enqueue(mockResponse)

        runBlocking {
            val resp = retrofit.networkCall {
                fakeService.emptyGet().await()
            }
            assertEquals(resp.status, Status.Success)
        }
    }

}