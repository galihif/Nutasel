package com.giftech.terbit.data.mapper

import com.giftech.terbit.data.source.local.statics.model.NotificationEntity
import com.giftech.terbit.domain.enums.NotificationType
import com.giftech.terbit.domain.model.Notification
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationMapper @Inject constructor() {
    
    fun mapToDomain(input: List<NotificationEntity>): List<Notification> {
        return input.map { entity ->
            mapToDomain(entity)
        }
    }
    
    private fun mapToDomain(input: NotificationEntity): Notification {
        return Notification(
            id = input.id,
            title = input.title,
            message = input.message,
            triggerTime = input.triggerTime,
            type = NotificationType.fromTypeId(input.type),
        )
    }
    
}