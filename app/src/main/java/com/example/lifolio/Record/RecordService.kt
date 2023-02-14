package com.example.lifolio.Record

import com.example.lifolio.Record.model.GetBigCategoryRes
import com.example.lifolio.Record.model.GetSmallCategoryRes
import com.example.lifolio.util.model.BaseRes
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface RecordService {
    @Multipart
    @POST("my")
    fun createRecord(
        @Part imageUrl: List<MultipartBody.Part?>,
        @Part("postMyLifolioReq") postMyLifolioReq: RequestBody
    ): Call<BaseRes>

    @GET("category/{userId}")
    fun getBigCategoryList (@Path("userId") userId: Int): Call<GetBigCategoryRes>

    @GET("category/view/{userId}")
    fun getSmallCategoryList (@Path("userId") userId: Int): Call<GetSmallCategoryRes>
}