package com.giftech.terbit.notif

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.Calendar

object RemindersManager {
    
    fun startReminder(
        context: Context,
        reminderId: Int,
        notificationTitle: String,
        notificationMessage: String,
        notificationDeepLink: String?,
        notificationType: String,
        dateTimeMillis: Long,
    ) {
        val dateTime = Calendar.getInstance().apply {
            timeInMillis = dateTimeMillis
        }
        
        val intent =
            Intent(context.applicationContext, AlarmReceiver::class.java).let { intent ->
                intent.putExtra(Constants.NotificationParams.NOTIFICATION_ID, reminderId)
                intent.putExtra(Constants.NotificationParams.NOTIFICATION_TITLE, notificationTitle)
                intent.putExtra(
                    Constants.NotificationParams.NOTIFICATION_MESSAGE,
                    notificationMessage
                )
                intent.putExtra(
                    Constants.NotificationParams.NOTIFICATION_DEEP_LINK,
                    notificationDeepLink
                )
                intent.putExtra(Constants.NotificationParams.NOTIFICATION_TYPE, notificationType)
                intent.putExtra(
                    Constants.NotificationParams.NOTIFICATION_DATE_TIME_MILLIS,
                    dateTimeMillis
                )
                
                PendingIntent.getBroadcast(
                    context.applicationContext,
                    reminderId,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT,
                )
            }
        
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setAlarmClock(
            AlarmManager.AlarmClockInfo(
                dateTime.timeInMillis,
                intent,
            ),
            intent,
        )
    }
    
    fun stopReminder(
        context: Context,
        reminderId: Int,
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(
                context,
                reminderId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT,
            )
        }
        alarmManager.cancel(intent)
    }
    
}