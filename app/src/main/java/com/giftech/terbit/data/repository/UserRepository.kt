package com.giftech.terbit.data.repository

import com.giftech.terbit.data.source.local.datastore.UserDataStore
import com.giftech.terbit.domain.model.User
import com.giftech.terbit.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDataStore: UserDataStore,
) : IUserRepository {
    
    override suspend fun saveUser(user: User) {
        userDataStore.updateUser(user)
    }
    
    override suspend fun getUser(): Flow<User> {
        return userDataStore.userFlow
    }
    
}