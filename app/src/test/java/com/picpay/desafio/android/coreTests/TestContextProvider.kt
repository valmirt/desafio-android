package com.picpay.desafio.android.coreTests

import com.picpay.desafio.android.network.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestContextProvider : CoroutineContextProvider() {
    override val Main: CoroutineContext
        get() = Dispatchers.Unconfined

    override val IO: CoroutineContext
        get() = Dispatchers.Unconfined
}