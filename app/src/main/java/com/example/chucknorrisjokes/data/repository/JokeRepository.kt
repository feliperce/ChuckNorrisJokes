package com.example.chucknorrisjokes.data.repository

import com.example.chucknorrisjokes.data.remote.response.RandomJokeResponse
import com.example.chucknorrisjokes.data.remote.service.ChuckService
import com.example.chucknorrisjokes.data.statushandler.Resource
import com.example.chucknorrisjokes.extension.networkCall
import org.koin.core.KoinComponent
import retrofit2.Retrofit

class JokeRepository(private val retrofit: Retrofit, private val chuckService: ChuckService) {


    suspend fun getJokeCategories(): Resource<List<String>> =
        retrofit.networkCall {
            chuckService.getCategories().await()
        }

    suspend fun getRandomJokeByCategory(category: String): Resource<RandomJokeResponse> =
        retrofit.networkCall {
            chuckService.getRandomJokeByCategory(category).await()
        }

}