package com.picpay.desafio.android.di

import com.google.gson.GsonBuilder
import com.picpay.desafio.android.network.CoroutineContextProvider
import com.picpay.desafio.android.network.PicPayService
import com.picpay.desafio.android.repository.UserRepository
import com.picpay.desafio.android.ui.MainViewModel
import com.picpay.desafio.android.useCase.UserUseCase
import okhttp3.mockwebserver.MockWebServer
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun coreInstrumentationModules(baseTestApi: String) = module {

    single {
        Retrofit.Builder()
            .baseUrl(baseTestApi)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    single { get<Retrofit>().create(PicPayService::class.java) }

    single { CoroutineContextProvider() }

    factory { MockWebServer() }
}

val repoModules = module {
    single { UserRepository(api = get()) }
}

val useCaseModules = module {
    single { UserUseCase(userRepository = get()) }
}

val viewModelModules = module {
    viewModel { MainViewModel(userUseCase = get(), coroutineContextProvider = get()) }
}