package com.giftech.terbit.data.source.local

import com.giftech.terbit.data.source.local.statics.NotificationData
import com.giftech.terbit.data.source.local.statics.model.NotificationEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationLocalDataSource @Inject constructor(
    private val notificationData: NotificationData,
){
    
    fun getAll(): Flow<List<NotificationEntity>> {
        return flowOf(notificationData.notificationList)
    }
    
}