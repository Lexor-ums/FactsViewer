package com.example.factviewer.domain.network

sealed class RequestResult<out T: Any> {
    data class Success<out T : Any>(val data: T) : RequestResult<T>()
    data class Error(val exception: Exception) : RequestResult<Nothing>()
}