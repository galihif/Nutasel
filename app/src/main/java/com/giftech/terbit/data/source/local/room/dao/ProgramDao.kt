package com.giftech.terbit.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.giftech.terbit.data.source.local.room.entity.ProgramEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgramDao {
    
    @Query("SELECT * FROM ProgramEntity")
    fun getAll(): Flow<List<ProgramEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(programEntity: ProgramEntity)
    
}