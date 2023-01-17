package com.example.lifolio.EditCategory.models

data class BigCategory(
    val bigCategory : String,
    var innerList : MutableList<SmallCategory>
)
