package com.example.lifolio.Category

import com.example.lifolio.Category.model.PostCategoryReq
import com.example.lifolio.util.model.BaseRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface CategoryService {
    @POST("category/list/{userId}")
    fun addBigCategory(@Body postCategoryReq: PostCategoryReq, @Path("userId") userId: Int): Call<BaseRes>
}