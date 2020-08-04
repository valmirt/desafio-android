package com.picpay.desafio.android.app

import com.picpay.desafio.android.PicPayApplication
import org.koin.core.module.Module

class TestMainApp: PicPayApplication() {

    override fun provideDependency() = listOf<Module>()
}