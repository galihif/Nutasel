package com.giftech.terbit.notif

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.giftech.terbit.domain.enums.NotificationType
import com.giftech.terbit.domain.util.toMillis
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.util.Calendar

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {
    
    // Sends notification when receives alarm and then reschedule the reminder again
    override fun onReceive(context: Context, intent: Intent) {
        val notificationId =
            intent.getIntExtra(Constants.NotificationParams.NOTIFICATION_ID, -1)
        val notificationTitle =
            intent.getStringExtra(Constants.NotificationParams.NOTIFICATION_TITLE).orEmpty()
        val notificationMessage =
            intent.getStringExtra(Constants.NotificationParams.NOTIFICATION_MESSAGE).orEmpty()
        val notificationDeepLink =
            intent.getStringExtra(Constants.NotificationParams.NOTIFICATION_DEEP_LINK)
        val notificationType =
            intent.getStringExtra(Constants.NotificationParams.NOTIFICATION_TYPE).orEmpty().let {
                NotificationType.fromTypeId(it)
            }
        val notificationDateTimeMillis =
            intent.getLongExtra(Constants.NotificationParams.NOTIFICATION_DATE_TIME_MILLIS, -1)
        
        // Reschedule the reminder
        if (notificationType == NotificationType.DAILY_TIPS) {
            RemindersManager.startReminder(
                context = context.applicationContext,
                reminderId = notificationId,
                notificationTitle = notificationTitle,
                notificationMessage = notificationMessage,
                notificationDeepLink = notificationDeepLink,
                notificationType = notificationType.typeId,
                dateTimeMillis = Calendar.getInstance().apply {
                    timeInMillis = LocalDateTime.now().toMillis()
                    add(Calendar.DAY_OF_MONTH, 1)
                }.timeInMillis,
            )
        }
        
        // Skip notification if the date time is in the past
        val isPastDateTime = Calendar.getInstance().apply {
            // Give reminders at the same minute, but at a slightly different second,
            // a chance to fire a notification
            add(Calendar.MINUTE, -1)
        }.timeInMillis > notificationDateTimeMillis
        if (isPastDateTime) {
            return
        }
        
        val notificationManager = ContextCompat.getSystemService(
            context,
            NotificationManager::class.java,
        ) as NotificationManager
        notificationManager.showNotification(
            applicationContext = context,
            channelId = notificationType.typeId,
            notificationId = notificationId,
            title = notificationTitle,
            message = notificationMessage,
        )
    }
    
}