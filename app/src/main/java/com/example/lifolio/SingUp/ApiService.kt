package com.example.lifolio.SingUp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET ("user/check/userId")
    fun getCheckUserId(
        @Query("userId") userId : String
    ): Call<Response>

    @GET ("/user/check/nickname")
    fun getCheckUserNickname(
        @Query("nickName") nickName : String
    ): Call<CheckNicknameResponse>
}