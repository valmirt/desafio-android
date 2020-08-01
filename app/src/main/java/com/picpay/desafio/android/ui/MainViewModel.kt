package com.picpay.desafio.android.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.picpay.desafio.android.base.BaseViewModel
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.network.CoroutineContextProvider
import com.picpay.desafio.android.network.StatusNetwork
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
            val result = userUseCase.getUserList()

            when (result.status) {
                StatusNetwork.SUCCESS -> result.data?.let { _userList.postValue(it) }
                StatusNetwork.ERROR -> result.message?.let { _messageError.postValue(it) }
            }
        }
    }
}