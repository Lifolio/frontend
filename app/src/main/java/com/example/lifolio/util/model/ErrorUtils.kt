package com.example.lifolio.util.model

import com.example.lifolio.JWT.ApiClient
import okhttp3.ResponseBody

class ErrorUtils {
    companion object {
        inline fun <reified T> getErrorResponse(errorBody: ResponseBody): T? {
            return ApiClient.retrofit.responseBodyConverter<T>(
                T::class.java,
                T::class.java.annotations
            ).convert(errorBody)
        }
    }
}