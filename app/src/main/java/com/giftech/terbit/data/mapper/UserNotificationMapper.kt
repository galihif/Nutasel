package com.giftech.terbit.data.mapper

import com.giftech.terbit.data.source.local.room.entity.UserNotificationEntity
import com.giftech.terbit.domain.enums.NotificationType
import com.giftech.terbit.domain.model.UserNotification
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserNotificationMapper @Inject constructor() {
    
    fun mapToDomain(input: List<UserNotificationEntity>): List<UserNotification> {
        return input.map { entity ->
            mapToDomain(entity)
        }
    }
    
    private fun mapToDomain(input: UserNotificationEntity): UserNotification {
        return UserNotification(
            notificationId = input.notificationId,
            title = input.title,
            message = input.message,
            deepLink = input.deepLink,
            idLink = input.idLink,
            readStatus = input.readStatus,
            triggerDateTimeInMillis = input.triggerDateTimeInMillis,
            notificationType = NotificationType.fromTypeId(input.notificationType),
            activeStatus = input.activeStatus,
            schedulingStatus = input.schedulingStatus,
            shownStatus = input.shownStatus,
        )
    }
    
}