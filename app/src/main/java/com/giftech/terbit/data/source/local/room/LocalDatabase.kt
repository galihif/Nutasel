package com.giftech.terbit.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.giftech.terbit.data.source.local.room.dao.FfqFoodDao
import com.giftech.terbit.data.source.local.room.dao.FfqResponseDao
import com.giftech.terbit.data.source.local.room.dao.ProgramDao
import com.giftech.terbit.data.source.local.room.entity.FfqFoodEntity
import com.giftech.terbit.data.source.local.room.entity.FfqResponseEntity
import com.giftech.terbit.data.source.local.room.entity.ProgramEntity

@Database(
    entities = [
        FfqResponseEntity::class,
        FfqFoodEntity::class,
        ProgramEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class LocalDatabase : RoomDatabase() {
    
    companion object {
        const val DATABASE_NAME = "terbit.db"
    }
    
    abstract fun ffqFoodDao(): FfqFoodDao
    
    abstract fun ffqResponseDao(): FfqResponseDao
    
    abstract fun programDao(): ProgramDao
    
}