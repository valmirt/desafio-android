package com.picpay.desafio.android.network

sealed class Result<out T> {
    class Success<T>(val data: T?) : Result<T>()
    class Error<T>(val message: String) : Result<T>()
}