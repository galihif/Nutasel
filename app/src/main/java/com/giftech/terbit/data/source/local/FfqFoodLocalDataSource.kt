package com.giftech.terbit.data.source.local

import com.giftech.terbit.data.source.local.room.dao.FfqFoodDao
import com.giftech.terbit.data.source.local.room.entity.FfqFoodEntity
import com.giftech.terbit.data.source.local.statics.FfqFoodData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FfqFoodLocalDataSource(
    private val ffqFoodDao: FfqFoodDao,
) {
    
    suspend fun getAll() : Flow<List<FfqFoodEntity>> {
        return ffqFoodDao.getAll().map { listFromLocalDB ->
            FfqFoodData.get() + listFromLocalDB
        }
    }
    
    suspend fun insert(ffqFoodEntity: FfqFoodEntity) {
        ffqFoodDao.insert(ffqFoodEntity)
    }
    
}