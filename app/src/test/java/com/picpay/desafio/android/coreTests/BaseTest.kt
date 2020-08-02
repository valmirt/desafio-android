package com.picpay.desafio.android.coreTests

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
open class BaseTest {

    @get:Rule
    val testCoroutineRule =
        TestCoroutineRule()
}