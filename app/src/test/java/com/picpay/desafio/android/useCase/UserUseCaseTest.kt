package com.picpay.desafio.android.useCase

import com.nhaarman.mockitokotlin2.*
import com.picpay.desafio.android.coreTests.BaseTest
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.network.Result
import com.picpay.desafio.android.network.StatusNetwork
import com.picpay.desafio.android.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class UserUseCaseTest : BaseTest() {
    private val repository = mock<UserRepository>()

    private lateinit var userUseCase: UserUseCase

    @Before
    fun setup() {
        userUseCase = UserUseCase(repository)
    }

    @Test
    fun `Should return a Result with content`() = testCoroutineRule.runBlockingTest {
        //Given
        val result = Result<List<User>>(data = null, status = StatusNetwork.SUCCESS, message = null)
        whenever(repository.getUsers()).thenReturn(result)

        //When
        val useCaseResult = userUseCase.getUserList()

        //Then
        verify(repository, times(1)).getUsers()
        assertEquals(result, useCaseResult)
    }
}