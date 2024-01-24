package com.example.chitalnya.data.model

sealed interface Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>
    data class Error(val exception: Throwable) : Resource<Nothing>
    data object Loading : Resource<Nothing>
}