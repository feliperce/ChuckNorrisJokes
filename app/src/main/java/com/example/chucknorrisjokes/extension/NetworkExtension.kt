package com.example.chucknorrisjokes.extension

import android.util.Log
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.data.remote.response.ErrorResponse
import com.example.chucknorrisjokes.data.statushandler.Resource
import com.example.chucknorrisjokes.exception.ConnectionTimeoutException
import com.example.chucknorrisjokes.exception.GenericException
import com.example.chucknorrisjokes.exception.NoConnectionException
import com.example.chucknorrisjokes.exception.ServiceException
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
                Resource.error(GenericException(R.string.error_generic))
            } else {
                val errorConverter: Converter<ResponseBody, ErrorResponse> =
                    this.responseBodyConverter(ErrorResponse::class.java, arrayOf())

                val error = errorConverter.convert(errorBody)
                Resource.error(ServiceException(-1, error))
            }

        }
    } catch (e: Exception) {

        when (e) {
            is UnknownHostException -> {
                Resource.error(NoConnectionException(R.string.no_connection))
            }
            is TimeoutException -> {
                Resource.error(ConnectionTimeoutException(R.string.error_timeout))
            }
            else -> Resource.error(GenericException(R.string.error_generic))
        }
    }
}