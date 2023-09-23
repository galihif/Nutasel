package com.giftech.terbit.domain.model

import com.giftech.terbit.domain.enums.NotificationType

data class Notification (
    val id: Int,
    val title: String,
    val message: String,
    val triggerTime: String?,
    val type: NotificationType,
) {
    
    val triggerHours: Int
        get() = triggerTime?.split(":")?.get(0)?.toInt() ?: 8
    
    val triggerMinutes: Int
        get() = triggerTime?.split(":")?.get(1)?.toInt() ?: 0
    
}