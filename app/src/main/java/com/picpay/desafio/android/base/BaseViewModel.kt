package com.picpay.desafio.android.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.network.CoroutineContextProvider
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

open class BaseViewModel(
    private val coroutineContext: CoroutineContextProvider
) : ViewModel(), KoinComponent {
    private var _spinner = MutableLiveData<Boolean>()
    protected var _messageError = MutableLiveData<String>()

    val spinner: LiveData<Boolean>
        get() = _spinner
    val messageError: LiveData<String>
        get() = _messageError

    protected fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch(coroutineContext.IO) {
            _spinner.postValue(true)
            block.invoke()
            _spinner.postValue(false)
        }
    }
}