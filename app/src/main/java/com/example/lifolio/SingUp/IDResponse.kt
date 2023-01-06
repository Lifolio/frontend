package com.example.lifolio.SingUp

// 챠코 - #IDFoundActivity와 연결

data class IDResponse(
    val isSuccess: Boolean,
    val code: Int,
    val messaage: String,
    val result: Object,
    val username: String
)
