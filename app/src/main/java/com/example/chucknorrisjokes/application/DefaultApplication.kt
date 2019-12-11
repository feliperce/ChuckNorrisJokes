package com.example.chucknorrisjokes.application

import android.app.Application
import com.example.chucknorrisjokes.di.*
import org.koin.core.context.startKoin

class DefaultApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(arrayListOf(
                retrofitModule,
                respositoryModule,
                viewModelModule
            ))
        }
    }
}