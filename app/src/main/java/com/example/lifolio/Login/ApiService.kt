package com.example.lifolio.Login

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("user/login")
    fun requestLogin(@Body body : RequestLogin) : Call<ResponseLogin>

    @POST("user/kakao/certificate")
    fun requestKakaoLogin(@Body body : RequestKakaoLogin) : Call<ResponseKakaoLogin>
}