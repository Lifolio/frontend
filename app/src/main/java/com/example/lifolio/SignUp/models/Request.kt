package com.example.lifolio.SignUp.models

import com.google.gson.annotations.SerializedName

data class Request(
    @SerializedName("username")
    val username : String,

    @SerializedName("password")
    val password : String,

    @SerializedName("name")
    val name : String,

    @SerializedName("nickname")
    val nickname: String,

    @SerializedName("phone")
    val phone : String
)
