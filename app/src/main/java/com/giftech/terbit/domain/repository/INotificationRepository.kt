package com.giftech.terbit.domain.repository

import com.giftech.terbit.domain.model.Notification

interface INotificationRepository {
    
    fun getAll(): List<Notification>
    
}