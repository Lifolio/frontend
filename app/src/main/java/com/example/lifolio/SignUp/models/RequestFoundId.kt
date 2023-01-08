package com.example.lifolio.SignUp.models

import com.google.gson.annotations.SerializedName

data class RequestFoundId(
    @SerializedName("name")
    val name : String,

    @SerializedName("phone")
    val phone : String
)
