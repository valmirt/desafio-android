package com.picpay.desafio.android.ui

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.base.BaseViewModelTest
import com.picpay.desafio.android.helpers.TestContextProvider
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.network.Result
import com.picpay.desafio.android.useCase.UserUseCase
import com.picpay.desafio.android.util.ErrorUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest : BaseViewModelTest() {

    private val userUseCase = mock<UserUseCase>()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel(
            userUseCase = userUseCase,
            coroutineContextProvider = TestContextProvider()
        )
    }

    @Test
    fun `Should return a list of users and no error message`() = testCoroutineRule.runBlockingTest {
        //Given
        val userList = listOf(mockUser())
        val result = Result.Success(data = userList)
        whenever(userUseCase.getUserList()).thenReturn(result)

        //When
        viewModel.getUserList()

        //Then
        verify(userUseCase, times(1)).getUserList()
        assertEquals(userList, viewModel.userList.value)
        assertEquals(null, viewModel.messageError.value)
        assertEquals(false, viewModel.spinner.value)
    }

    @Test
    fun `Should return a default error message`() = testCoroutineRule.runBlockingTest {
        //Given
        val result = Result.Error<List<User>>(message = ErrorUtil.ERROR_DEFAULT)
        whenever(userUseCase.getUserList()).thenReturn(result)

        //When
        viewModel.getUserList()

        //Then
        verify(userUseCase, times(1)).getUserList()
        assertEquals(null, viewModel.userList.value)
        assertEquals(ErrorUtil.ERROR_DEFAULT, viewModel.messageError.value)
        assertEquals(false, viewModel.spinner.value)
    }

    private fun mockUser(): User =
        User (
            id = 1001,
            name = "Eduardo Santos",
            img = "",
            username = "@eduardo.santos"
        )
}