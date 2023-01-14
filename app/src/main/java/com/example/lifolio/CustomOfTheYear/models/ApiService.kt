package com.example.lifolio.CustomOfTheYear.models

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST ("home/goal")
    fun addOfTheYearGoal(
        @Header ("Authorization") Authorization : String?,
        @Body body : OfTheGoalRequest
    ): Call<Response>

    @GET ("home/goal")
    fun getOfTheYear(
        @Header ("Authorization") Authorization : String?,
        @Path("userId") userId : Long
    ): Call<Response>
}