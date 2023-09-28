package com.giftech.terbit

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.giftech.terbit.domain.enums.NotificationType
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }
    
    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationType.values().toMutableList().forEach {
                createNotificationChannel(
                    context = this@MainApplication,
                    id = it.typeId,
                    name = it.channelName,
                )
            }
    
            createNotificationChannel(
                context = this@MainApplication,
                id = getString(R.string.notif_channel_id_systemchannel),
                name = getString(R.string.notif_channel_name_systemchannel),
                importance = NotificationManager.IMPORTANCE_NONE,
                lockscreenVisibility = Notification.VISIBILITY_SECRET,
                canBubble = false,
                canShowBadge = false,
            )
        }
    }
    
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(
        context: Context,
        id: String,
        name: String,
        description: String? = null,
        importance: Int = NotificationManager.IMPORTANCE_HIGH,
        lockscreenVisibility: Int = Notification.VISIBILITY_PUBLIC,
        canBubble: Boolean = true,
        canShowBadge: Boolean = true,
    ) {
        val channel = NotificationChannel(
            id,
            name,
            importance,
        )
        
        channel.description = description
        
        // Should enable floating notification
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) channel.setAllowBubbles(canBubble)
        // Should show badge on app icon
        channel.setShowBadge(canShowBadge)
        // Should show notification on lock screen
        channel.lockscreenVisibility = lockscreenVisibility
        
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
    
}