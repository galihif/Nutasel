package com.giftech.terbit.data.source.local

import com.giftech.terbit.data.source.local.room.dao.FfqResponseDao
import com.giftech.terbit.data.source.local.room.entity.FfqResponseEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FfqResponseLocalDataSource @Inject constructor(
    private val ffqResponseDao: FfqResponseDao,
) {
    
    fun getAll(): Flow<List<FfqResponseEntity>> {
        return ffqResponseDao.getAll()
    }
    
    suspend fun insert(ffqResponseEntity: FfqResponseEntity) {
        ffqResponseDao.insert(ffqResponseEntity)
    }
    
    suspend fun update(ffqResponseEntity: FfqResponseEntity) {
        ffqResponseDao.update(ffqResponseEntity)
    }
    
}