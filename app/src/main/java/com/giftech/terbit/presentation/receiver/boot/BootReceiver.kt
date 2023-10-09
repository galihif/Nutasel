package com.giftech.terbit.presentation.receiver.boot

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.giftech.terbit.presentation.util.startNotificationRestartService

class BootReceiver : BroadcastReceiver() {
    
    override fun onReceive(
        context: Context,
        intent: Intent,
    ) {
        when (intent.action) {
            "android.intent.action.BOOT_COMPLETED" -> {
                startNotificationRestartService(context)
            }
        }
    }
    
}