package com.example.chucknorrisjokes.extension

import android.content.Context
import com.example.chucknorrisjokes.exception.ErrorException

fun Context.getErrorStringOrNull(errorException: ErrorException): String? {
    return if (errorException.msg != -1 && errorException.errorResponse != null) {
        errorException.errorResponse.error
    } else if (errorException.msg != -1) {
        this.getString(errorException.msg)
    } else {
        null
    }
}