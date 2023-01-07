package com.example.lifolio.SignUp

import com.google.gson.annotations.SerializedName

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
    val number: Int,

    @SerializedName("userId")
    val userId : Long,

    @SerializedName("accessToken")
    val accessToken : String,

    @SerializedName("username")
    val username : String
)
