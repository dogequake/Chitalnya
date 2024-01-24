package com.example.chitalnya.data.interfaces

import com.example.chitalnya.data.model.ApiResponse
import com.example.chitalnya.data.model.Book
import com.example.chitalnya.data.model.User
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksInterface{
    @Headers("Content-Type:application/json")
    @GET("books")
    fun getAllBooks(): Call<ApiResponse<Book>>

    @Headers("Content-Type:application/json")
    @GET("books/{id}")
    fun getBooksById(@Path("id") id: Int): Call<Book>

    @Headers("Content-Type:application/json")
    @PUT("books/{id}")
    fun putBook(@Path("id") id: Int, @Body body: JsonObject): Call<Book>

    @Headers("Content-Type:application/json")
    @POST("books")
    fun postBook(@Body body: JsonObject): Call<Book>

    @Headers("Content-Type:application/json")
    @DELETE("books/{id}")
    fun deleteBook(@Path("id") id: Int): Call<Book>
}