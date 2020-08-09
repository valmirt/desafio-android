package com.picpay.desafio.android.base

import com.picpay.desafio.android.network.Result
import com.picpay.desafio.android.util.ErrorUtil
import com.picpay.desafio.android.util.ErrorUtil.ERROR_DEFAULT
import retrofit2.Response

open class BaseRepository {

    suspend fun <T> safeCallApi (call: suspend () -> Response<T>): Result<T> {
        return try {
            val response = call.invoke()

            if (response.isSuccessful) {
                Result.Success(response.body())
            } else {
                Result.Error(ErrorUtil.parseError(response).message)
            }
        } catch (error: Exception) {
            Result.Error(ERROR_DEFAULT)
        }
    }
}