package com.example.lifolio.CustomOfTheYear.models

import com.google.gson.annotations.SerializedName

data class OfTheGoalRequest(
    @SerializedName("goal")
    val goal : String
)
