package com.picpay.desafio.android.repository

import com.picpay.desafio.android.base.BaseRepository
import com.picpay.desafio.android.network.PicPayService

class UserRepository (
    private val api: PicPayService
): BaseRepository() {

    suspend fun getUsers() = safeCallApi {
        api.getUsers()
    }
}