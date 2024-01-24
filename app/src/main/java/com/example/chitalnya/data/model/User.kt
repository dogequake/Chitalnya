package com.example.chitalnya.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("idUser"    ) var idUser    : Int? = null,
    @SerializedName("roleId"    ) var roleId    : Int? = null,
    @SerializedName("login"    ) var login    : String? = null,
    @SerializedName("password" ) var password : String? = null,
    @SerializedName("surname"  ) var surname  : String? = null,
    @SerializedName("name"     ) var name     : String? = null,
    @SerializedName("userpic"  ) var userpic  : String? = null

//    @SerializedName("first_name") var firstName: String? = null,
//    @SerializedName("last_name") var lastName: String? = null,
//    @SerializedName("middle_name") var middleName: String? = null,
//    @SerializedName("login") var login: String? = null,
//    @SerializedName("password") var password: String? = null,
//    @SerializedName("role") var role: Int? = 1,
//    @SerializedName("avatar") var avatar: String? = null,

)
