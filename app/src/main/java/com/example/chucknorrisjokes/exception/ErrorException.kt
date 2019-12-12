package com.example.chucknorrisjokes.exception

import com.example.chucknorrisjokes.data.remote.response.ErrorResponse
import java.lang.Exception

open class ErrorException(val msg: Int = -1, val errorResponse: ErrorResponse? = null) : Exception()