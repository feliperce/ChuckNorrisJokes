package com.example.chucknorrisjokes.data.statushandler

import com.example.chucknorrisjokes.exception.ErrorException
import java.lang.Exception

sealed class Status {
    object Success : Status()
    class Error(val exception: ErrorException? = null) : Status()
    class Loading(val isLoading: Boolean) : Status()
}