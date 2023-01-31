package com.example.lifolio.util.model

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MethodCallback {

    companion object {

        inline fun <G, O : G, reified E : G> generalCallback(crossinline callback: (response: G?) -> Unit): Callback<O> {

            return object : Callback<O> {
                override fun onFailure(call: Call<O>, t: Throwable) {
                    Log.d("TestAPI", "Failed API call with call: $call exception: $t")
                }

                override fun onResponse(call: Call<O>, response: Response<O>) {

                    if (response.isSuccessful) {
                        callback(response.body())
                    } else {
                        if (response.errorBody() != null) {

                            when (response.code()) {
                                400 -> {
                                    val errorBody: E? =
                                        ErrorUtils.getErrorResponse<E>(response.errorBody()!!)

                                    if (errorBody != null) {
                                        callback(errorBody)
                                    } else {
                                        callback(null)
                                    }
                                }
                                else -> {
                                    callback(null)
                                }
                            }
                        } else {
                            callback(null)
                        }
                    }
                }
            }
        }
    }
}