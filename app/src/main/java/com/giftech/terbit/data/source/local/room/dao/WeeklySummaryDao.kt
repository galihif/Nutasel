package com.giftech.terbit.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.giftech.terbit.data.source.local.room.entity.WeeklySummaryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeeklySummaryDao {
    
    @Query("SELECT * FROM WeeklySummaryEntity")
    fun getAll(): Flow<List<WeeklySummaryEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weeklySummaryEntity: WeeklySummaryEntity)
    
}