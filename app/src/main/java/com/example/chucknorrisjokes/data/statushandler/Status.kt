package com.example.chucknorrisjokes.data.statushandler

sealed class Status {
    object Success : Status()
    class Error(val errorStatus: ErrorStatus?) : Status()
    object Loading : Status()
}