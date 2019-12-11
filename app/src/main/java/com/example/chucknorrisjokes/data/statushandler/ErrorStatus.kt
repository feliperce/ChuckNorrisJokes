package com.example.chucknorrisjokes.data.statushandler

import com.example.chucknorrisjokes.data.remote.response.ErrorResponse

sealed class ErrorStatus {
    class Generic(val msg: Int) : ErrorStatus()
    class NoNetwork(val msg: Int) : ErrorStatus()
    class Timeout(val msg: Int) : ErrorStatus()
    class Service(val errorResponse: ErrorResponse?) : ErrorStatus()
}

