package com.giftech.terbit.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.giftech.terbit.data.source.local.room.entity.UserNotificationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserNotificationDao {
    
    @Query("SELECT * FROM UserNotificationEntity")
    fun getAll(): Flow<List<UserNotificationEntity>>
    
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(userNotificationEntity: UserNotificationEntity)
    
    @Query("UPDATE UserNotificationEntity SET readStatus = :readStatus WHERE notificationId = :notificationId AND idLink = :idLink")
    suspend fun updateReadStatus(notificationId: Int, idLink: Int, readStatus: Boolean)
    
    @Query("UPDATE UserNotificationEntity SET activeStatus = :activeStatus WHERE notificationId = :notificationId AND idLink = :idLink")
    suspend fun updateActiveStatus(notificationId: Int, idLink: Int, activeStatus: Boolean)
    
    @Query("UPDATE UserNotificationEntity SET schedulingStatus = :schedulingStatus WHERE notificationId = :notificationId AND idLink = :idLink")
    suspend fun updateSchedulingStatus(notificationId: Int, idLink: Int, schedulingStatus: Boolean)
    
    @Query("UPDATE UserNotificationEntity SET shownStatus = :shownStatus WHERE notificationId = :notificationId AND idLink = :idLink")
    suspend fun updateShownStatus(notificationId: Int, idLink: Int, shownStatus: Boolean)
    
    @Query("DELETE FROM UserNotificationEntity WHERE notificationId = :notificationId AND idLink = :idLink")
    suspend fun delete(notificationId: Int, idLink: Int)
    
}