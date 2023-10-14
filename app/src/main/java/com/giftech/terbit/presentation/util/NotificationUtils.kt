package com.giftech.terbit.presentation.util

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.giftech.terbit.R
import com.giftech.terbit.presentation.MainActivity

fun Notification.show(
    context: Context,
    id: Int,
) {
    val notificationManager = ContextCompat.getSystemService(
        context,
        NotificationManager::class.java,
    ) as NotificationManager
    notificationManager.notify(
        id,
        this,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
fun buildNotification(
    context: Context,
    channelId: String,
    id: Int,
    title: String,
    message: String,
): Notification {
    val contentIntent = Intent(
        context,
        MainActivity::class.java,
    )
    val pendingIntent = PendingIntent.getActivity(
        context,
        id,
        contentIntent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
    )
    
    val builder = NotificationCompat.Builder(
        context,
        channelId,
    ).apply {
        setContentIntent(pendingIntent)
        
        setSmallIcon(R.drawable.ic_logo_notif_24)
        color = context.getColor(R.color.md_theme_light_primary)
        
        setAutoCancel(true)
        setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        priority = NotificationCompat.PRIORITY_HIGH
        
        if (title.isNotEmpty()) {
            setContentTitle(title)
        }
        if (message.isNotEmpty()) {
            setContentText(message)
            setStyle(NotificationCompat.BigTextStyle().bigText(message))
        }
    }
    return builder.build()
}

fun buildHiddenNotification(
    context: Context,
    channelId: String,
    message: String,
): Notification {
    val builder = NotificationCompat.Builder(
        context,
        channelId,
    ).apply {
        setSmallIcon(R.drawable.ic_logo_notif_24)
        color = context.getColor(R.color.md_theme_light_primary)
        
        setSilent(true)
        setVibrate(null)
        setOngoing(true)
        setVisibility(NotificationCompat.VISIBILITY_SECRET)
        priority = NotificationCompat.PRIORITY_MIN
        
        setContentText(message)
    }
    return builder.build()
}