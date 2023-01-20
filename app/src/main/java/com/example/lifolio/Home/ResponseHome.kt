package com.example.lifolio.Home

data class ResponseHome(
    val isSuccess : Boolean,
    val code : Int,
    val message : String,
)
//
//data class Result(
//    val colorStatus: Int,
//    val goal : String,
//    val mainLifolio : MainLifolio?
//)
//
//data class MainLifolio(
//    val month: Int,
//    val star: Int,
//    val customLifolio : CustomLifolio?
//)
//
//data class CustomLifolio(
//    val customId : Long,
//    val concept : Int,
//    val emoji : String,
//    val customName : String,
//)
