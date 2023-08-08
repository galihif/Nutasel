package com.giftech.terbit.data.source.local

import com.giftech.terbit.data.source.local.room.dao.FfqAnswerDao
import com.giftech.terbit.data.source.local.room.entity.FfqAnswerEntity
import kotlinx.coroutines.flow.Flow

class FfqAnswerLocalDataSource(
    private val ffqAnswerDao: FfqAnswerDao,
) {

    suspend fun getAll(): Flow<List<FfqAnswerEntity>> {
        return ffqAnswerDao.getAll()
    }
    
    suspend fun insert(ffqAnswerEntity: FfqAnswerEntity) {
        ffqAnswerDao.insert(ffqAnswerEntity)
    }
    
    suspend fun update(ffqAnswerEntity: FfqAnswerEntity) {
        ffqAnswerDao.update(ffqAnswerEntity)
    }
    
    suspend fun delete(ffqAnswerEntity: FfqAnswerEntity) {
        ffqAnswerDao.delete(ffqAnswerEntity)
    }

}