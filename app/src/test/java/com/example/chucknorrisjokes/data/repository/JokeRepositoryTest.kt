package com.example.chucknorrisjokes.data.repository

import com.example.chucknorrisjokes.data.remote.service.MockService
import com.example.chucknorrisjokes.utils.MockRetrofitBuilder
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import retrofit2.Retrofit


class JokeRepositoryTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var retrofit: Retrofit
    private lateinit var fakeService: MockService
    private lateinit var jokeRepository: JokeRepository

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        mockWebServer = MockWebServer()
        retrofit = MockRetrofitBuilder.build(mockWebServer.url("/"))
        fakeService = MockRetrofitBuilder.buildMockService(retrofit)

    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getJokeCategories_onStart_returnLoading() {



    }

    @Test
    fun getRandomJokeByCategory() {

    }
}