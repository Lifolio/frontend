package com.example.lifolio.My

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("my")
    fun myLife( // My 조회
        @Query("Authorization") Authorization : String
    ): Call<ResponseMy>
}