package com.picpay.desafio.android.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.coreTests.BaseTest
import com.picpay.desafio.android.network.PicPayService
import com.picpay.desafio.android.network.StatusNetwork
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response


@ExperimentalCoroutinesApi
class UserRepositoryTest: BaseTest() {
    private val api = mock<PicPayService>()
    lateinit var userRepository: UserRepository

    @Before
    fun setup() {
        userRepository = UserRepository(api)
    }

    @Test
    fun `Test if safeCall return a success Result`() = testCoroutineRule.runBlockingTest {
        //Given
        whenever(api.getUsers()).thenReturn(Response.success(emptyList()))

        //When
        val result = userRepository.getUsers()

        //Then
        assertEquals(StatusNetwork.SUCCESS, result.status)
    }

    @Test
    fun `Test if safeCall return a error Result`() = testCoroutineRule.runBlockingTest {
        //Given
        whenever(api.getUsers()).thenReturn(null)

        //When
        val result = userRepository.getUsers()

        //Then
        assertEquals(StatusNetwork.ERROR, result.status)
    }
}