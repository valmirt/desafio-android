package com.picpay.desafio.android.useCase

import com.picpay.desafio.android.repository.UserRepository

class UserUseCase(private val userRepository: UserRepository) {

    suspend fun getUserList() = userRepository.getUsers()
}