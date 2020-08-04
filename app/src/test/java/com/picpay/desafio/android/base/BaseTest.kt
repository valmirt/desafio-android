package com.picpay.desafio.android.base

import com.picpay.desafio.android.helpers.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
open class BaseTest {

    @get:Rule
    val testCoroutineRule =
        TestCoroutineRule()
}