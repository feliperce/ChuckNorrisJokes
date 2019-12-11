package com.example.chucknorrisjokes.data.statushandler

sealed class Status {
    object Success : Status()
    class Error(val errorStatus: ErrorStatus? = null) : Status()
    object Loading : Status()
}