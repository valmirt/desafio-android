package com.picpay.desafio.android.util

import com.nhaarman.mockitokotlin2.mock
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class ErrorUtilTest {
    private val responseBody = mock<ResponseBody>()

    @Test
    fun `Should return a properly message and code`() {
        //Given
        val response = Response.error<Any>(418, responseBody)

        //When
        val error = ErrorUtil.parseError(response)

        //Then
        assertEquals("I'm a teapot", error.message)
        assertEquals(response.code(), error.statusCode)
    }
}