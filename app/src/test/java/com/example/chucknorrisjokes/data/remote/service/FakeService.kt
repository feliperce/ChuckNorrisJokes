package com.example.chucknorrisjokes.data.remote.service

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface FakeService {

    @GET("get/empty")
    fun emptyGet() : Deferred<Response<Unit>>

}