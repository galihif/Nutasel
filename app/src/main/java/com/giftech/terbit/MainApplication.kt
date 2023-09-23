package com.giftech.terbit

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
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
            ContextCompat.getSystemService(this, NotificationManager::class.java)?.apply {
                NotificationType.values().toMutableList().forEach {
                    createChannel(
                        id = it.typeId,
                        name = it.channelName,
                    )
                }
                
                createChannel(
                    id = getString(R.string.notif_channel_id_systemchannel),
                    name = getString(R.string.notif_channel_name_systemchannel),
                    importance = NotificationManager.IMPORTANCE_NONE,
                )
            }
        }
    }
    
    @RequiresApi(Build.VERSION_CODES.O)
    private fun NotificationManager.createChannel(
        id: String,
        name: String,
        importance: Int = NotificationManager.IMPORTANCE_HIGH,
    ) {
        val channel = NotificationChannel(
            id,
            name,
            importance,
        )
        createNotificationChannel(channel)
    }
    
}