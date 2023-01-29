package com.example.lifolio.Category

import android.provider.ContactsContract.Data
import com.example.lifolio.Category.model.PostCategoryReq
import com.example.lifolio.util.model.BaseRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface PostCategoryService {
    @POST("category/list/{userId}")
    fun addBigCategory(@Body postCategoryReq: PostCategoryReq, @Path("userId") userId: Int): Call<BaseRes>
}