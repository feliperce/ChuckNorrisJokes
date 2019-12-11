package com.example.chucknorrisjokes.di

import com.example.chucknorrisjokes.BuildConfig
import com.example.chucknorrisjokes.data.remote.service.ChuckService
import com.example.chucknorrisjokes.data.remote.service.RetrofitBuilder
import com.example.chucknorrisjokes.data.repository.JokeRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {

    fun retrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else HttpLoggingInterceptor.Level.NONE

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(RetrofitBuilder.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    fun jokeService(retrofit: Retrofit): ChuckService {
        return retrofit.create(ChuckService::class.java)
    }

    single { retrofit() }
    single { jokeService(get()) }
}

val respositoryModule = module {
    factory { JokeRepository(get(), get()) }
}

val viewModelModule = module {

}