package com.example.chucknorrisjokes.extension

import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.data.remote.service.RetrofitBuilder
import com.example.chucknorrisjokes.data.statushandler.ErrorStatus
import com.example.chucknorrisjokes.data.statushandler.Resource
import retrofit2.Response
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

inline fun <reified T> RetrofitBuilder.networkCall(block: RetrofitBuilder.() -> Response<T>): Resource<T> {

    return try {
        val response = block()

        if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            var errorResponse: String? = null

            response.errorBody()?.let { f ->
                errorResponse = f.string()
            }

            if (errorResponse.isNullOrEmpty()) {
                Resource.error(ErrorStatus.GENERIC, R.string.error_generic, null)
            } else {
                Resource.error(
                    ErrorStatus.SERVICE,
                    R.string.error_generic,
                    com.google.gson.Gson().fromJson(errorResponse, T::class.java)
                )
            }
        }
    } catch (e: Exception) {

        when (e) {
            is UnknownHostException -> {
                Resource.error(ErrorStatus.NO_NETWORK, R.string.no_connection, null)
            }
            is TimeoutException -> {
                Resource.error(ErrorStatus.TIMEOUT, R.string.error_timeout, null)
            }
            else -> Resource.error(ErrorStatus.GENERIC, R.string.error_generic, null)
        }
    }
}