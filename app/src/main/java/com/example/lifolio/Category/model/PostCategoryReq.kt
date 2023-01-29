package com.example.lifolio.Category.model


import com.google.gson.annotations.SerializedName

data class PostCategoryReq(
    @SerializedName("colorId")
    val colorId: Int,

    @SerializedName("subtitle")
    val subtitle: List<String?>?,

    @SerializedName("title")
    val title: String
)