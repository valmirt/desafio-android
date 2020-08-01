package com.picpay.desafio.android.network

class Result<out T>(val data: T?, val message: String?, val status: StatusNetwork) {

    companion object {
        fun <T> success(data: T?) = Result(data, null, StatusNetwork.SUCCESS)

        fun <T> error(message: String) = Result<T>(null, message, StatusNetwork.ERROR)
    }
}