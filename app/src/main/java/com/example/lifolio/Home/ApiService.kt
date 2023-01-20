package com.example.lifolio.Home

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {
    @GET("home/{userId}")
    fun homeView( //홈 화면 조회
        @Header("Authorization") Authorization: String,
        @Path("userId") userId: Long
    ) :Call<ResponseHome>

    @GET("home/graph/{userId}/{customId}")
    fun graphView( //커스텀 라이폴리오 그래프형 조회
        @Header("Authorization") Authorization: String,
        @Path("userId") userId : Long,
        @Path("customId") customId : Long
    ) :Call<ResponseGraph>

    @GET("home/custom/{userId}/{customId}")
    fun top4View( //중요도 top4 조회
        @Header("Authorization") Authorization: String,
        @Path("userId") userId : Long,
        @Path("customId") customId : Long
    )   :Call<ResponseTop>
}