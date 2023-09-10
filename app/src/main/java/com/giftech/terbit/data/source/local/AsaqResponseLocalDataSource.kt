package com.giftech.terbit.data.source.local

import com.giftech.terbit.data.source.local.room.dao.AsaqResponseDao
import com.giftech.terbit.data.source.local.room.entity.AsaqResponseEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AsaqResponseLocalDataSource @Inject constructor(
    private val asaqResponseDao: AsaqResponseDao,
) {
    
    fun getAll(): Flow<List<AsaqResponseEntity>> {
        return asaqResponseDao.getAll()
    }
    
    suspend fun insert(asaqResponseEntity: AsaqResponseEntity) {
        asaqResponseDao.insert(asaqResponseEntity)
    }
    
}