package com.giftech.terbit.domain.repository

import com.giftech.terbit.domain.model.UserNotification
import kotlinx.coroutines.flow.Flow

interface IUserNotificationRepository {
    
    fun getAll(): Flow<List<UserNotification>>
    
    suspend fun insert(
        notificationId: Int,
        title: String,
        message: String,
        deepLink: String?,
        idLink: Int,
        readStatus: Boolean,
        triggerDateTimeInMillis: Long,
        notificationType: String,
        activeStatus: Boolean,
    )
    
    suspend fun updateReadStatus(
        notificationId: Int,
        idLink: Int,
        readStatus: Boolean,
    )
    
    suspend fun updateActiveStatus(
        notificationId: Int,
        idLink: Int,
        activeStatus: Boolean,
    )
    
    suspend fun delete(
        notificationId: Int,
        idLink: Int,
    )
    
}