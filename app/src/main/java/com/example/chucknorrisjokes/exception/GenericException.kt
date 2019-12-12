package com.example.chucknorrisjokes.exception

import com.example.chucknorrisjokes.data.remote.response.ErrorResponse
import java.lang.Exception

class GenericException(msg: Int, errorResponse: ErrorResponse? = null) :
    ErrorException(msg)