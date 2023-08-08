package com.giftech.terbit.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.giftech.terbit.data.source.local.room.entity.FfqFoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FfqFoodDao {
    
    @Query("SELECT * FROM FfqFoodEntity")
    suspend fun getAll(): Flow<List<FfqFoodEntity>>
    
    @Insert
    suspend fun insert(ffqFoodEntity: FfqFoodEntity)
    
}