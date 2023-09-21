package com.giftech.terbit.notif

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.app.NotificationCompat
import com.giftech.terbit.MainActivity
import com.giftech.terbit.R

fun startDailyTipsNotification(context: Context) {
    /* NotificationData.notificationList
        .filter {
            it.type == NotificationType.DAILY_TIPS
        }
        .forEach {
            RemindersManager.startReminder(
                context = context,
                id = it.id,
                time = it.triggerTime ?: "08:00",
            )
        } */
}

@OptIn(ExperimentalMaterial3Api::class)
fun NotificationManager.showNotification(
    applicationContext: Context,
    channelId: String,
    notificationId: Int,
    title: String,
    message: String,
) {
    val contentIntent = Intent(applicationContext, MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(
        applicationContext,
        notificationId,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )
    val builder = NotificationCompat.Builder(applicationContext, channelId)
        .setContentTitle(title)
        .setContentText(message)
        .setSmallIcon(R.drawable.ic_logo_notif)
        .setStyle(
            NotificationCompat.BigTextStyle()
                .bigText(message)
        )
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
    
    notify(notificationId, builder.build())
}