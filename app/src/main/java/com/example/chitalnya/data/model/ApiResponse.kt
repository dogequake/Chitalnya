package com.example.chitalnya.data.model

//val success: Boolean,
//val products: List<T>,

data class ApiResponse<T>(
    var data: List<T>,
    var success: Boolean? = null
)
