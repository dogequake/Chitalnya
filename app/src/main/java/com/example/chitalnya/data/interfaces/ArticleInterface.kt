package com.example.chitalnya.data.interfaces

import com.example.chitalnya.data.model.ApiResponse
import com.example.chitalnya.data.model.Article
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ArticleInterface {
    @Headers("Content-Type:application/json")
    @GET("Articles")
    fun getArticles(): Call<ApiResponse<Article>>

    @Headers("Content-Type:application/json")
    @GET("Articles/{id}")
    fun getArticleById(@Path("id") id: Int): Call<Article>
}