package com.example.lifolio.Login

data class ResponseLogin(
    val isSuccess : Boolean,
    val code : Int,
    val message : String,
    val result : SomeData?
)

data class SomeData(
    val userId: Int,
    val accessToken : String
)