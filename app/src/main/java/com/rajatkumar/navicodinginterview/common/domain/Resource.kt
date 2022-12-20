package com.rajatkumar.navicodinginterview.common.domain

sealed class Resource<T>(
    val data: T? = null
) {
    class Success<T>(data: T): Resource<T>(data)
    data class Error<T>(val error: AppError): Resource<T>()
    data class Loading<T>(val message: String? = null): Resource<T>()
}