package com.giftech.terbit.data.source.local

import com.giftech.terbit.data.source.local.room.dao.FfqFoodDao
import com.giftech.terbit.data.source.local.room.entity.FfqFoodEntity
import com.giftech.terbit.data.source.local.statics.FfqFoodData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FfqFoodLocalDataSource @Inject constructor(
    private val ffqFoodDao: FfqFoodDao,
    private val ffqFoodData: FfqFoodData,
) {
    
    fun getAll(): Flow<List<FfqFoodEntity>> {
        val listFromStatic = ffqFoodData.getAll()
        return ffqFoodDao.getAll().map { listFromLocalDB ->
            listFromStatic + listFromLocalDB
        }
    }
    
    suspend fun insert(ffqFoodEntity: FfqFoodEntity) {
        ffqFoodDao.insert(ffqFoodEntity)
    }
    
}