package com.example.chitalnya.data.model

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("idArticle"  ) var idArticle   : Int?    = null,
    @SerializedName("title"      ) var title       : String? = null,
    @SerializedName("description") var description : String? = null,
    @SerializedName("articlepic" ) var articlepic  : String? = null,
    @SerializedName("slogan"     ) var slogan      : String? = null
)
