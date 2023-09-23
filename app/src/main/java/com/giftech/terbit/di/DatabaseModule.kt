package com.giftech.terbit.di

import android.app.Application
import androidx.room.Room
import com.giftech.terbit.data.source.local.room.LocalDatabase
import com.giftech.terbit.data.source.local.room.dao.AsaqDao
import com.giftech.terbit.data.source.local.room.dao.AsaqResponseDao
import com.giftech.terbit.data.source.local.room.dao.FfqFoodDao
import com.giftech.terbit.data.source.local.room.dao.FfqResponseDao
import com.giftech.terbit.data.source.local.room.dao.ProgramDao
import com.giftech.terbit.data.source.local.room.dao.UserNotificationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Singleton
    @Provides
    fun provideDatabase(application: Application): LocalDatabase {
        return Room.databaseBuilder(
            application,
            LocalDatabase::class.java,
            LocalDatabase.DATABASE_NAME,
        ).fallbackToDestructiveMigration().build()
    }
    
    @Singleton
    @Provides
    fun provideAsaqDao(database: LocalDatabase): AsaqDao {
        return database.asaqDao()
    }
    
    @Singleton
    @Provides
    fun provideAsaqResponseDao(database: LocalDatabase): AsaqResponseDao {
        return database.asaqResponseDao()
    }
    
    @Singleton
    @Provides
    fun provideFfqFoodDao(database: LocalDatabase): FfqFoodDao {
        return database.ffqFoodDao()
    }
    
    @Singleton
    @Provides
    fun provideFfqResponseDao(database: LocalDatabase): FfqResponseDao {
        return database.ffqResponseDao()
    }
    
    @Singleton
    @Provides
    fun provideProgramDao(database: LocalDatabase): ProgramDao {
        return database.programDao()
    }
    
    @Singleton
    @Provides
    fun provideUserNotificationDao(database: LocalDatabase): UserNotificationDao {
        return database.userNotificationDao()
    }
    
}