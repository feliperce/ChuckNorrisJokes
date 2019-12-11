package com.example.chucknorrisjokes.extension

import android.util.Log
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.data.remote.response.ErrorResponse
import com.example.chucknorrisjokes.data.statushandler.ErrorStatus
import com.example.chucknorrisjokes.data.statushandler.Resource
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException


inline fun <reified T> Retrofit.networkCall(service: Retrofit.() -> Response<T>): Resource<T> {

    return try {

        val response = service()

        if (response.isSuccessful) {
            Resource.success(response.body())
        } else {

            val errorBody = response.errorBody()

            if (errorBody == null) {
                Resource.error(ErrorStatus.Generic(R.string.error_generic))
            } else {
                val errorConverter: Converter<ResponseBody, ErrorResponse> =
                    this.responseBodyConverter(ErrorResponse::class.java, arrayOf())

                val error = errorConverter.convert(errorBody)
                Resource.error(ErrorStatus.Service(error))
            }

        }
    } catch (e: Exception) {

        when (e) {
            is UnknownHostException -> {
                Resource.error(ErrorStatus.NoNetwork(R.string.no_connection))
            }
            is TimeoutException -> {
                Resource.error(ErrorStatus.Timeout(R.string.error_timeout))
            }
            else -> Resource.error(ErrorStatus.Generic(R.string.error_generic))
        }
    }
}