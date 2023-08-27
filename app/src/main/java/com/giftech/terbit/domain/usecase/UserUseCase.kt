package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.model.User
import com.giftech.terbit.domain.repository.IUserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {
    fun getUser() = userRepository.getUser()
    suspend fun saveUser(user: User) = userRepository.saveUser(user)
}