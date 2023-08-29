package com.giftech.terbit.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.giftech.terbit.data.source.local.room.entity.AsaqEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AsaqDao {

    @Query("SELECT * FROM AsaqEntity WHERE testType = 0")
    fun getPreTests(): Flow<List<AsaqEntity>>

    @Query("SELECT * FROM AsaqEntity WHERE testType = 1")
    fun getPostTest():Flow<List<AsaqEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(asaqEntities: List<AsaqEntity>)
}