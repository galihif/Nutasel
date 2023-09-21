package com.giftech.terbit.data.repository

import com.giftech.terbit.data.mapper.NotificationMapper
import com.giftech.terbit.data.source.local.NotificationLocalDataSource
import com.giftech.terbit.domain.model.Notification
import com.giftech.terbit.domain.repository.INotificationRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationRepository @Inject constructor(
    private val notificationLocalDataSource: NotificationLocalDataSource,
    private val notificationMapper: NotificationMapper,
) : INotificationRepository {
    
    override fun getAll(): List<Notification> {
        return notificationLocalDataSource.getAll().let {
            notificationMapper.mapToDomain(it)
        }
    }
    
}