package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.DIModules
import com.picpay.desafio.android.di.DIModules.coreModules
import com.picpay.desafio.android.di.DIModules.repoModules
import com.picpay.desafio.android.di.DIModules.useCaseModules
import com.picpay.desafio.android.di.DIModules.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

open class PicPayApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PicPayApplication)
            modules(provideDependency())
        }
    }

    open fun provideDependency() = listOf(coreModules, repoModules, useCaseModules, viewModelModules)
}