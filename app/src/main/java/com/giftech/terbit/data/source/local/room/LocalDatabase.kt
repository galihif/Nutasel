package com.giftech.terbit.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.giftech.terbit.data.source.local.room.dao.AsaqDao
import com.giftech.terbit.data.source.local.room.dao.AsaqResponseDao
import com.giftech.terbit.data.source.local.room.dao.FfqFoodDao
import com.giftech.terbit.data.source.local.room.dao.FfqResponseDao
import com.giftech.terbit.data.source.local.room.dao.ProgramDao
import com.giftech.terbit.data.source.local.room.dao.UserNotificationDao
import com.giftech.terbit.data.source.local.room.entity.AsaqEntity
import com.giftech.terbit.data.source.local.room.entity.AsaqResponseEntity
import com.giftech.terbit.data.source.local.room.entity.FfqFoodEntity
import com.giftech.terbit.data.source.local.room.entity.FfqResponseEntity
import com.giftech.terbit.data.source.local.room.entity.ProgramEntity
import com.giftech.terbit.data.source.local.room.entity.UserNotificationEntity

@Database(
    entities = [
        AsaqEntity::class,
        AsaqResponseEntity::class,
        FfqResponseEntity::class,
        FfqFoodEntity::class,
        ProgramEntity::class,
        UserNotificationEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class LocalDatabase : RoomDatabase() {
    
    companion object {
        const val DATABASE_NAME = "terbit.db"
    }
    
    abstract fun asaqDao(): AsaqDao
    
    abstract fun asaqResponseDao(): AsaqResponseDao
    
    abstract fun ffqFoodDao(): FfqFoodDao
    
    abstract fun ffqResponseDao(): FfqResponseDao
    
    abstract fun programDao(): ProgramDao
    
    abstract fun userNotificationDao(): UserNotificationDao
    
}