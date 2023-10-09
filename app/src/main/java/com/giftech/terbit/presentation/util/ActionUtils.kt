package com.giftech.terbit.presentation.util

import android.content.Context
import android.content.Intent
import com.giftech.terbit.presentation.service.notificationrestart.NotificationRestartService

fun startNotificationRestartService(context: Context) {
    Intent(
        context,
        NotificationRestartService::class.java,
    )
        .also { intent ->
            startService(
                context,
                intent,
            )
        }
}

fun stopNotificationRestartService(context: Context) {
    Intent(
        context,
        NotificationRestartService::class.java,
    )
        .also { intent ->
            context.stopService(intent)
        }
}