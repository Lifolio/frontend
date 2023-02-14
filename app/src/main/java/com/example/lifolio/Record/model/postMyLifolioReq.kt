package com.example.lifolio.Record.model


import com.google.gson.annotations.SerializedName

data class postMyLifolioReq(
    @SerializedName("category_id")
    val categoryId: Int,
    @SerializedName("content")
    val content: String?,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("goalofyear_id")
    val goalofyearId: Int?,
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?,
    @SerializedName("name")
    val name: List<String?>?,
    @SerializedName("star")
    val star: Int,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("title")
    val title: String
)