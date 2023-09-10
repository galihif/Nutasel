package com.giftech.terbit.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.giftech.terbit.data.source.local.room.entity.AsaqResponseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AsaqResponseDao {
    
    @Query("SELECT * FROM AsaqResponseEntity")
    fun getAll(): Flow<List<AsaqResponseEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(asaqResponseEntity: AsaqResponseEntity)
    
}