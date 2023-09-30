package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.model.User
import com.giftech.terbit.domain.repository.IUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: IUserRepository,
) {
    
    suspend fun getUser() = userRepository.getUser()
        .flowOn(Dispatchers.IO)
    
    suspend fun saveUser(user: User) = userRepository.saveUser(user)
    
}