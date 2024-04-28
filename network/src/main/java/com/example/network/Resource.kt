package com.example.network

sealed class Resource<out R> {
    class Success<out T>(val data: T) : Resource<T>()
    class Error<out T>(val message: String) : Resource<T>()
    object Loading : Resource<Nothing>()
}
