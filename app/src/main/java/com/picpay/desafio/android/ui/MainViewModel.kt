package com.picpay.desafio.android.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.picpay.desafio.android.base.BaseViewModel
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.network.CoroutineContextProvider
import com.picpay.desafio.android.network.Result
import com.picpay.desafio.android.useCase.UserUseCase

class MainViewModel(
    private val userUseCase: UserUseCase,
    coroutineContextProvider: CoroutineContextProvider
) : BaseViewModel(coroutineContextProvider) {
    private var _userList =  MutableLiveData<List<User>>()

    val userList: LiveData<List<User>>
        get() = _userList

    fun getUserList() {
        launchDataLoad {
            when (val result = userUseCase.getUserList()) {
                is Result.Success<List<User>> -> _userList.postValue(result.data)
                is Result.Error<*> -> _messageError.postValue(result.message)
            }
        }
    }
}