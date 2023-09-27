package com.giftech.terbit.domain.repository

import com.giftech.terbit.domain.model.Notification
import kotlinx.coroutines.flow.Flow

interface INotificationRepository {
    
    fun getAll(): Flow<List<Notification>>
    
}