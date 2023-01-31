package com.example.lifolio.Login

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("user/login")
    fun requestLogin(@Body body : RequestLogin) : Call<ResponseLogin>

    @POST("user/kakao/certificate")
    fun requestKakaoLogin(@Body body : RequestKakaoLogin) : Call<ResponseKakaoLogin>

    @POST("user/fcm")
    fun postFcmToken( // sms인증
        @Query("token") token : String
    ): Call<StringResponse>

    @POST("auth/kakao/logIn")
    fun requestKakao(
        @Body body: RequestKakaoLogin
    ) : Call<ResponseLogin>

    @POST("auth/naver/logIn")
    fun requestNaver(@Body body : RequestKakaoLogin) : Call<ResponseLogin>
}