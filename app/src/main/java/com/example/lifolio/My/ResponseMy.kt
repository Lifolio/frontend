package com.example.lifolio.My

data class ResponseMy(
    val isSuccess : Boolean,
    val code : Int,
    val message : String,
    val result : Result?
)

data class Result(
    val name: String,
    val lifolioCnt : Int,
    val bestCategory: BestCategory?
)

data class BestCategory(
    val color: String,
    val category: String,
    val url: String,
    val title: String,
    val start: Int,
    val archive: Archive?
)

data class Archive(
    val folioId: Long,
    val color: String,
    val category: String,
)
