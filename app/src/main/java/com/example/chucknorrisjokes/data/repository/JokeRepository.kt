package com.example.chucknorrisjokes.data.repository

import com.example.chucknorrisjokes.data.remote.response.RandomJokeResponse
import com.example.chucknorrisjokes.data.remote.service.ChuckService
import com.example.chucknorrisjokes.data.statushandler.Resource
import com.example.chucknorrisjokes.extension.networkCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import org.koin.core.KoinComponent
import retrofit2.Retrofit

class JokeRepository(private val retrofit: Retrofit, private val chuckService: ChuckService) {


    suspend fun getJokeCategories(): Flow<Resource<List<String>>> = flow {
        emit(retrofit.networkCall {
            chuckService.getCategories().await()
        })
    }.onStart {
        emit(Resource.loading())
    }


    suspend fun getRandomJokeByCategory(category: String): Flow<Resource<RandomJokeResponse>> = flow {
        emit(retrofit.networkCall {
            chuckService.getRandomJokeByCategory(category).await()
        })
    }.onStart {
        emit(Resource.loading())
    }


}