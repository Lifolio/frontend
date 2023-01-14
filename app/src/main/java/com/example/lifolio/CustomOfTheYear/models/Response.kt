package com.example.lifolio.CustomOfTheYear.models

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("isSuccess")
    val isSuccess : Boolean,

    @SerializedName("code")
    val code : Int,

    @SerializedName("message")
    val message : String,

    @SerializedName("result")
    val result : List<Result>
)

data class Result(
    @SerializedName("year")
    val year : Int,

    @SerializedName("goal")
    val goal : String,

    @SerializedName("createDate")
    val createDate : String
)
