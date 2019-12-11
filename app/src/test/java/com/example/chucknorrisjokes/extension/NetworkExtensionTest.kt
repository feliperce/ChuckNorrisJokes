package com.example.chucknorrisjokes.extension

import com.example.chucknorrisjokes.BuildConfig
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.data.remote.service.ChuckService
import com.example.chucknorrisjokes.data.remote.service.FakeService
import com.example.chucknorrisjokes.data.statushandler.ErrorStatus
import com.example.chucknorrisjokes.data.statushandler.Status
import com.example.chucknorrisjokes.di.respositoryModule
import com.example.chucknorrisjokes.di.retrofitModule
import com.example.chucknorrisjokes.di.viewModelModule
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication
import org.koin.test.AutoCloseKoinTest
import org.koin.test.KoinTest
import org.koin.test.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class NetworkExtensionTest : AutoCloseKoinTest() {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var retrofit: Retrofit
    private lateinit var fakeService: FakeService

    private fun buildService() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else HttpLoggingInterceptor.Level.NONE

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        fakeService = retrofit.create(FakeService::class.java)
    }


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

        buildService()
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