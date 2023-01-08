package com.example.lifolio.Login

data class ResponseKakaoLogin(
    val isSuccess : Boolean,
    val code : Int,
    val message : String,
    val result : KakaoData?
)

data class KakaoData(
    val userId: Long,
    val email: String,
    val nickname: String,
    val type: String,
)