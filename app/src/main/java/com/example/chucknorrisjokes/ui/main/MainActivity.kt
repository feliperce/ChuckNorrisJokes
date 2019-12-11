package com.example.chucknorrisjokes.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.data.remote.service.RetrofitBuilder
import com.example.chucknorrisjokes.data.repository.JokeRepository
import com.example.chucknorrisjokes.extension.networkCall
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val jokeRepository: JokeRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            /*val c = RetrofitBuilder(applicationContext)
            val res = c.networkCall {
                c.chuckService.getRandomJokeByCategory("dev").await()
            }

            Log.d("Main", res.message.toString())*/

            val bbb = jokeRepository.getJokeCategories()
            Log.d("Main", bbb.message.toString())

            val ccc = jokeRepository.getRandomJokeByCategory("dev")
            Log.d("Main", "dev")


        }
    }
}
