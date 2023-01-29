package com.example.lifolio.util.model


import com.google.gson.annotations.SerializedName

data class BaseRes(
    @SerializedName("code")
    val code: Int?,

    @SerializedName("isSuccess")
    val isSuccess: Boolean?,

    @SerializedName("message")
    val message: String?,

    @SerializedName("result")
    val result: String?
)