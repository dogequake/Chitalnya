package com.example.chitalnya.data.datasource

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//https://c16e-213-109-48-21.ngrok.io/api
object ServiceBuilder {
    private var gson = GsonBuilder()
        .setLenient()
        .create()
    private val client = OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://6981-217-151-225-238.ngrok-free.app/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }

    //public var loginBoolean = false
}