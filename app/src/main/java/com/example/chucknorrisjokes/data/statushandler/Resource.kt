package com.example.chucknorrisjokes.data.statushandler

import java.lang.Exception

data class Resource<out T>(val status: Status, val data: T? = null, val message: Int = -1) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.Success,
                data,
                -1
            )
        }

        fun <T> error(exception: Exception? = null): Resource<T> {
            return Resource(
                Status.Error(exception)
            )
        }

        fun <T> loading(isLoading: Boolean = false): Resource<T> {
            return Resource(
                Status.Loading(isLoading)
            )
        }
    }
}