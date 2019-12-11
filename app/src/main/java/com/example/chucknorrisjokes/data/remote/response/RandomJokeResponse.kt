package com.example.chucknorrisjokes.data.remote.response

data class RandomJokeResponse(
    val categories: List<String>? = arrayListOf(),
    val created_at: String?,
    val icon_url: String?,
    val id: String?,
    val updated_at: String?,
    val url: String?,
    val value: String?
)