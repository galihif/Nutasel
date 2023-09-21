package com.giftech.terbit.domain.model

import com.giftech.terbit.domain.enums.NotificationType

data class UserNotification(
    val notificationId: Int,
    val title: String,
    val message: String,
    val deepLink: String?,
    val idLink: Int,
    val readStatus: Boolean,
    val triggerDateTimeInMillis: Long,
    val notificationType: NotificationType,
    val activeStatus: Boolean,
) {
    
    val reminderId
        get() = idLink.let { notificationId + it }
    
}