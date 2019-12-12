package com.example.chucknorrisjokes.data.remote.response

data class ErrorResponse(
    val error: String?,
    val message: String?,
    val path: String?,
    val status: Int?,
    val timestamp: String?
)