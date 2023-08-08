package com.giftech.terbit.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.giftech.terbit.data.source.local.room.entity.FfqAnswerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FfqAnswerDao {
    
    @Query("SELECT * FROM FfqAnswerEntity")
    suspend fun getAll(): Flow<List<FfqAnswerEntity>>
    
    @Insert
    suspend fun insert(ffqAnswerEntity: FfqAnswerEntity)
    
    @Update
    suspend fun update(ffqAnswerEntity: FfqAnswerEntity)
    
    @Delete
    suspend fun delete(ffqAnswerEntity: FfqAnswerEntity)
    
}