package com.example.chitalnya.data.model

data class Book(
    val idBook: Int,
    val categoryId: Int,
    val bookpic: String,
    val maincolorBookpic: String,
    val author: String,
    val title: String,
    val description: String,
    val countPage: Int,
    val copyright: String,
    val publisher: String,
    val translator: String
)
