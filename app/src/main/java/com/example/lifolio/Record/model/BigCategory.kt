package com.example.lifolio.Record.model


import com.google.gson.annotations.SerializedName

data class BigCategory(
    @SerializedName("categoryId")
    val categoryId: Int,
    @SerializedName("categoryName")
    val categoryName: String
)