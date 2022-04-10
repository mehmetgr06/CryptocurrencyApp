package com.example.cryptocurrencyapp.common

sealed class Result<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Result<T>(data)
    class Error<T>(data: T? = null, errorMessage: String) : Result<T>(data, errorMessage)
    class Loading<T>(data: T? = null) : Result<T>(data)
}