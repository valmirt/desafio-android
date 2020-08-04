package com.picpay.desafio.android.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.BuildConfig.BASE_URL
import com.picpay.desafio.android.network.CoroutineContextProvider
import com.picpay.desafio.android.network.PicPayService
import com.picpay.desafio.android.repository.UserRepository
import com.picpay.desafio.android.ui.MainViewModel
import com.picpay.desafio.android.useCase.UserUseCase
import com.picpay.desafio.android.util.NetworkUtil
import com.picpay.desafio.android.util.NetworkUtil.CACHE_SIZE
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DIModules {

    val coreModules = module {
        single<Gson> { GsonBuilder().create() }

        single {
            OkHttpClient.Builder()
                .cache(Cache(get<Context>().cacheDir, CACHE_SIZE))
                .addInterceptor { NetworkUtil.defineCache(it, get()) }
                .build()
        }

        single<Retrofit> {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(get())
                .addConverterFactory(GsonConverterFactory.create(get()))
                .build()
        }

        single { CoroutineContextProvider() }

        //API
        single { get<Retrofit>().create(PicPayService::class.java) }
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
}