package com.example.lifolio.SignUp

import com.example.lifolio.Login.RequestLogin
import com.example.lifolio.Login.ResponseLogin
import com.example.lifolio.SignUp.models.Request
import com.example.lifolio.SignUp.Response
import com.example.lifolio.SignUp.models.RequestFoundId
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {
    @GET ("user/check/userId")
    fun getCheckUserId( // 아이디 중복체크
        @Query("userId") userId : String
    ): Call<Response>

    @GET ("user/check/nickname")
    fun getCheckUserNickname( // 닉네임 중복체크
        @Query("nickName") nickName : String
    ): Call<Response>

    @GET ("user/check/sendSMS")
    fun getSMS( // sms인증
        @Query("to") to : String
    ): Call<Response>

    @POST ("user/signup")
    fun createNewUser( // 신규 회원가입
        @Body body : Request
    ): Call<Response>

    @POST ("user/find")
    fun foundUserId(
        @Body body : RequestFoundId
    ): Call<Response>
}