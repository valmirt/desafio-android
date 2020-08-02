package com.picpay.desafio.android.coreTests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
open class BaseViewModel : BaseTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()
}