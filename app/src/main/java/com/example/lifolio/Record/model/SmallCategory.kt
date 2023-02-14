package com.example.lifolio.Record.model


import com.google.gson.annotations.SerializedName

data class SmallCategory(
    @SerializedName("categoryId")
    val categoryId: Int,
    @SerializedName("categoryName")
    val categoryName: String,
    @SerializedName("colorName")
    val colorName: String,
    @SerializedName("subCategoryList")
    val subCategoryList: List<String>?
)