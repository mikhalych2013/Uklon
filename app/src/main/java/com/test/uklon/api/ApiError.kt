package com.test.uklon.api

import com.test.uklon.App
import com.test.uklon.R
import retrofit2.HttpException
import java.net.UnknownHostException

class ApiError(error: Throwable) {

    companion object {

        const val RESPONSE_CODE_NOT_SPECIFIED = 0

        const val TYPE_UNEXPECTED_EXCEPTION = 0
        const val TYPE_HTTP_EXCEPTION = 1
        const val TYPE_UNKNOWN_HOST_EXCEPTION = 2

    }

    val code: Int

    val message: String

    val type: Int

    init {
        when(error) {
            is HttpException -> {
                type = TYPE_HTTP_EXCEPTION
                code = error.code()
                message = error.localizedMessage
            }
            is UnknownHostException -> {
                type = TYPE_UNKNOWN_HOST_EXCEPTION
                code = RESPONSE_CODE_NOT_SPECIFIED
                message = App.instance.getString(R.string.common_http_error_unknown_host_exception)
            }
            else -> {
                type = TYPE_UNEXPECTED_EXCEPTION
                code = RESPONSE_CODE_NOT_SPECIFIED
                message = error.localizedMessage
                        ?: "${App.instance.getString(R.string.common_http_error_unexpected_exception)} (${error.javaClass.canonicalName})"
            }
        }
    }

}