package com.picpay.desafio.android.di

import com.picpay.desafio.android.di.DIModules.coreModules
import com.picpay.desafio.android.network.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject

class KoinModulesTest : KoinTest {

    private val coroutineContext: CoroutineContextProvider by inject()

    @Before
    fun setup() {
        startKoin { modules(coreModules) }
    }

    @Test
    fun `Test if CoroutineContextProvider was properly created as a single`() {
        assertEquals(Dispatchers.IO, coroutineContext.IO)
        assertEquals(Dispatchers.Main, coroutineContext.Main)

        val secondInstance: CoroutineContextProvider = get()

        assertEquals(coroutineContext.IO, secondInstance.IO)
        assertEquals(coroutineContext.Main, secondInstance.Main)
        assertEquals(coroutineContext, secondInstance)
    }

    @After
    fun after() {
        stopKoin()
    }
}