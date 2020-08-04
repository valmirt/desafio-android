package com.picpay.desafio.android.di

fun generateTestApp(baseApi: String) =
    listOf(
        coreInstrumentationModules(baseApi),
        useCaseModules,
        repoModules,
        viewModelModules
    )