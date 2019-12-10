package com.example.chucknorrisjokes.data.remote.service

import com.example.chucknorrisjokes.data.remote.response.Category
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ChuckService {
    companion object {
        const val RANDOM_JOKE_PATH = "jokes/random"
        const val CATEGORIES_PATH = "jokes/categories"
    }

    @GET(CATEGORIES_PATH)
    fun getRandomJoke() : Deferred<Response<List<Category>>>

    @GET(CATEGORIES_PATH)
    fun getCategories() : Deferred<Response<List<Category>>>
}