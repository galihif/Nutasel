package com.giftech.terbit.data.source.local.statics.model

data class NotificationEntity(
    val id: Int,
    val title: String,
    val message: String,
    val triggerTime: String?,
    val type: String,
)