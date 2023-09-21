package com.giftech.terbit

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.content.ContextCompat
import com.giftech.terbit.domain.enums.NotificationType
import com.giftech.terbit.notif.startDailyTipsNotification
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
        startDailyTipsNotification(this)
    }
    
    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ContextCompat.getSystemService(this, NotificationManager::class.java)?.apply {
                NotificationType.values().forEach {
                    val channel = NotificationChannel(
                        it.typeId,
                        it.channelName,
                        NotificationManager.IMPORTANCE_HIGH,
                    )
                    createNotificationChannel(channel)
                }
            }
        }
    }
    
}