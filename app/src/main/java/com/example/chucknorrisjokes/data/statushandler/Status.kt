package com.example.chucknorrisjokes.data.statushandler

import java.lang.Exception

sealed class Status {
    object Success : Status()
    class Error(val exception: Exception? = null) : Status()
    class Loading(val isLoading: Boolean) : Status()
}