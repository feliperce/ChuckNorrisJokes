package com.example.chucknorrisjokes.data.remote.service

import android.content.Context
import com.example.chucknorrisjokes.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.KoinComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder(var context: Context) : KoinComponent {

    companion object {
        const val BASE_URL = BuildConfig.ENDPOINT_URL
    }

    lateinit var retrofit: Retrofit
    lateinit var chuckService: ChuckService

    init {
        build()
    }

    fun build() {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else HttpLoggingInterceptor.Level.NONE

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        chuckService = retrofit.create(ChuckService::class.java)
    }
}