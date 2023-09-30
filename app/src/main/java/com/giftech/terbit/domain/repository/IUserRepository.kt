package com.giftech.terbit.domain.repository

import com.giftech.terbit.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    
    suspend fun saveUser(user: User)
    
    suspend fun getUser(): Flow<User>
    
}