package com.example.chucknorrisjokes.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.data.repository.JokeRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
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

            val bbb = jokeRepository.getJokeCategories().collect {

            }

            val ccc = jokeRepository.getRandomJokeByCategory("dev")
            Log.d("Main", "dev")


        }
    }
}
