package com.giftech.terbit.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.giftech.terbit.data.source.local.room.entity.FfqResponseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FfqResponseDao {
    
    @Query("SELECT * FROM FfqResponseEntity")
    fun getAll(): Flow<List<FfqResponseEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ffqResponseEntity: FfqResponseEntity)
    
}