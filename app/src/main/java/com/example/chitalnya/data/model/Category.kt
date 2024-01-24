package com.example.chitalnya.data.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("idCategory") var idCategory   : Int?    = null,
    @SerializedName("name"      ) var nameCategory : String? = null,
)
