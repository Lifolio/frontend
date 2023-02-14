package com.example.lifolio.Record.model


import com.google.gson.annotations.SerializedName

data class GetSmallCategoryRes(
    @SerializedName("code")
    val code: Int,
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val smallCategory: SmallCategory?
)