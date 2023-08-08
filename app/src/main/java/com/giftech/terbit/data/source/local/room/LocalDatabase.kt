package com.giftech.terbit.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.giftech.terbit.data.source.local.room.dao.FfqAnswerDao
import com.giftech.terbit.data.source.local.room.dao.FfqFoodDao
import com.giftech.terbit.data.source.local.room.entity.FfqAnswerEntity
import com.giftech.terbit.data.source.local.room.entity.FfqFoodEntity

@Database(
    entities = [
        FfqAnswerEntity::class,
        FfqFoodEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    
    abstract fun ffqAnswerDao(): FfqAnswerDao
    
    abstract fun ffqFoodDao(): FfqFoodDao
    
}