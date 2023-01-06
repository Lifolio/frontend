package com.example.lifolio.SingUp

data class IDResponse(
    val isSuccess: Boolean,
    val code: Int,
    val messaage: String,
    val result: Object,
    val username: String
)
