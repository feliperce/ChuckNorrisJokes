package com.example.chucknorrisjokes.data.remote.service

import com.example.chucknorrisjokes.data.remote.response.RandomJokeResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ChuckService {
    companion object {
        const val RANDOM_JOKE_PATH = "jokes/random"
        const val CATEGORIES_PATH = "jokes/categories"
    }

    @GET(RANDOM_JOKE_PATH)
    fun getRandomJoke() : Deferred<Response<RandomJokeResponse>>

    @GET(CATEGORIES_PATH)
    fun getCategories() : Deferred<Response<List<String>>>
}