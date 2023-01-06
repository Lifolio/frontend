package com.example.lifolio.SingUp

import com.google.gson.annotations.SerializedName
import java.util.Objects

data class Response(
    @SerializedName("isSuccess")
    val isSuccess : Boolean,

    @SerializedName("code")
    val code : Int,

    @SerializedName("message")
    val message : String,

    @SerializedName("result")
    val result : Result
)

data class Result(
    @SerializedName("number")
    val number: Int
)
