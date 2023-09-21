package com.giftech.terbit.notif

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootReceiver : BroadcastReceiver() {
    
    // Restart reminder alarms when the user's device reboots.
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            startDailyTipsNotification(context)
        }
    }
    
}