package com.giftech.terbit.data.repository

import com.giftech.terbit.data.mapper.UserNotificationMapper
import com.giftech.terbit.data.source.local.UserNotificationLocalDataSource
import com.giftech.terbit.data.source.local.room.entity.UserNotificationEntity
import com.giftech.terbit.domain.model.UserNotification
import com.giftech.terbit.domain.repository.IUserNotificationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserNotificationRepository @Inject constructor(
    private val userNotificationLocalDataSource: UserNotificationLocalDataSource,
    private val userNotificationMapper: UserNotificationMapper,
) : IUserNotificationRepository {
    
    override fun getAll(): Flow<List<UserNotification>> {
        return userNotificationLocalDataSource.getAll().map {
            userNotificationMapper.mapToDomain(it)
        }
    }
    
    override suspend fun insert(
        notificationId: Int,
        title: String,
        message: String,
        deepLink: String?,
        idLink: Int,
        readStatus: Boolean,
        triggerDateTimeInMillis: Long,
        notificationType: String,
        activeStatus: Boolean,
        schedulingStatus: Boolean,
        shownStatus: Boolean,
    ) {
        val userNotificationEntity = UserNotificationEntity(
            notificationId = notificationId,
            title = title,
            message = message,
            deepLink = deepLink,
            idLink = idLink,
            readStatus = readStatus,
            triggerDateTimeInMillis = triggerDateTimeInMillis,
            notificationType = notificationType,
            activeStatus = activeStatus,
            schedulingStatus = schedulingStatus,
            shownStatus = shownStatus,
        )
        userNotificationLocalDataSource.insert(userNotificationEntity)
    }
    
    override suspend fun updateReadStatus(
        notificationId: Int,
        idLink: Int,
        readStatus: Boolean,
    ) {
        userNotificationLocalDataSource.updateReadStatus(
            notificationId = notificationId,
            idLink = idLink,
            readStatus = readStatus,
        )
    }
    
    override suspend fun updateActiveStatus(
        notificationId: Int,
        idLink: Int,
        activeStatus: Boolean,
    ) {
        userNotificationLocalDataSource.updateActiveStatus(
            notificationId = notificationId,
            idLink = idLink,
            activeStatus = activeStatus,
        )
    }
    
    override suspend fun updateSchedulingStatus(
        notificationId: Int,
        idLink: Int,
        schedulingStatus: Boolean,
    ) {
        userNotificationLocalDataSource.updateSchedulingStatus(
            notificationId = notificationId,
            idLink = idLink,
            schedulingStatus = schedulingStatus,
        )
    }
    
    override suspend fun updateShownStatus(
        notificationId: Int,
        idLink: Int,
        shownStatus: Boolean,
    ) {
        userNotificationLocalDataSource.updateShownStatus(
            notificationId = notificationId,
            idLink = idLink,
            shownStatus = shownStatus,
        )
    }
    
    override suspend fun delete(
        notificationId: Int,
        idLink: Int,
    ) {
        userNotificationLocalDataSource.delete(
            notificationId = notificationId,
            idLink = idLink,
        )
    }
    
}