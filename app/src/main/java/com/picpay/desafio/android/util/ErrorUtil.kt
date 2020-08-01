package com.picpay.desafio.android.util

import com.picpay.desafio.android.model.ApiError
import retrofit2.Response

object ErrorUtil {
    const val ERROR_DEFAULT = "Something is wrong, try again later."

    fun parseError(response: Response<*>): ApiError {
        val rawResponse = response.raw()

        return ApiError(
            statusCode = rawResponse.code,
            message = getFriendlyMessage(code = rawResponse.code)
        )
    }

    private fun getFriendlyMessage(code: Int): String {
        return when (code) {
            /* implement code errors ex: 400, 401, 403... */
            418 -> "I'm a teapot"
            else -> ERROR_DEFAULT
        }
    }
}