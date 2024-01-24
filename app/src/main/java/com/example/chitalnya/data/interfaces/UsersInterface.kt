package com.example.chitalnya.data.interfaces

import com.example.chitalnya.data.model.ApiResponse
import com.example.chitalnya.data.model.User
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface UsersInterface {
    @Headers("Content-Type:application/json")
    @GET("users/{id}")
    fun getUserById(@Path("id") id: Int): Call<User>

    @Headers("Content-Type:application/json")
    @GET("users/")
    fun getAuth(
        @Query("login") login : String,
        @Query("password") password: String,
    ): Call<User>

    @Headers("Content-Type:application/json")
    @POST("users")
    fun createUser(@Body body: JsonObject): Call<User>
}