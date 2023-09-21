package com.giftech.terbit.data.source.local

import com.giftech.terbit.data.source.local.room.dao.UserNotificationDao
import com.giftech.terbit.data.source.local.room.entity.UserNotificationEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserNotificationLocalDataSource @Inject constructor(
    private val userNotificationDao: UserNotificationDao,
){
    
    fun getAll(): Flow<List<UserNotificationEntity>> {
        return userNotificationDao.getAll()
    }
    
    suspend fun insert(userNotification: UserNotificationEntity) {
        userNotificationDao.insert(userNotification)
    }
    
    suspend fun updateReadStatus(
        notificationId: Int,
        idLink: Int,
        readStatus: Boolean,
    ) {
        userNotificationDao.updateReadStatus(
            notificationId = notificationId,
            idLink = idLink,
            readStatus = readStatus,
        )
    }
    
    suspend fun updateActiveStatus(
        notificationId: Int,
        idLink: Int,
        activeStatus: Boolean,
    ) {
        userNotificationDao.updateActiveStatus(
            notificationId = notificationId,
            idLink = idLink,
            activeStatus = activeStatus,
        )
    }
    
    suspend fun delete(
        notificationId: Int,
        idLink: Int,
    ) {
        userNotificationDao.delete(
            notificationId = notificationId,
            idLink = idLink,
        )
    }
    
}