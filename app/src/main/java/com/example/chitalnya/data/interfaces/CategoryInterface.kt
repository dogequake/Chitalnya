package com.example.chitalnya.data.interfaces

import com.example.chitalnya.data.model.ApiResponse
import com.example.chitalnya.data.model.Category
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface CategoryInterface {
    @Headers("Content-Type:application/json")
    @GET("Categories")
    fun getAllCategory(): Call<ApiResponse<Category>>
}