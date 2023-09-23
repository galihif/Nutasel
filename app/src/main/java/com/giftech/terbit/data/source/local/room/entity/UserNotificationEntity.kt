package com.giftech.terbit.data.source.local.room.entity

import androidx.room.Entity

@Entity(
    tableName = "UserNotificationEntity",
    primaryKeys = ["notificationId", "idLink"],
)
data class UserNotificationEntity(
    val notificationId: Int,
    val title: String,
    val message: String,
    val deepLink: String?,
    val idLink: Int,
    val readStatus: Boolean,
    val triggerDateTimeInMillis: Long,
    val notificationType: String,
    val activeStatus: Boolean,
    val schedulingStatus: Boolean,
    val shownStatus: Boolean,
)