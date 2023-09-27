package com.giftech.terbit.data.repository

import com.giftech.terbit.data.mapper.NotificationMapper
import com.giftech.terbit.data.source.local.NotificationLocalDataSource
import com.giftech.terbit.domain.model.Notification
import com.giftech.terbit.domain.repository.INotificationRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationRepository @Inject constructor(
    private val notificationLocalDataSource: NotificationLocalDataSource,
    private val notificationMapper: NotificationMapper,
) : INotificationRepository {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAll(): Flow<List<Notification>> {
        return notificationLocalDataSource.getAll()
            .mapLatest {
                notificationMapper.mapToDomain(it)
            }
    }
    
}