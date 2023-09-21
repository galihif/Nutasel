package com.giftech.terbit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import com.giftech.terbit.notif.RemindersManager
import com.giftech.terbit.ui.TerbitApp
import com.giftech.terbit.ui.theme.TerbitTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    private val viewModel: MainViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TerbitTheme {
                TerbitApp()
            }
        }
        monitorNotification()
    }
    
    private fun monitorNotification() {
        viewModel.getAllUserNotification().observe(this) { userNotificationList ->
            val currentDateTime = Calendar.getInstance().apply {
                // Give reminders at the same minute, but at a slightly different second,
                // a chance to fire a notification
                add(Calendar.MINUTE, -1)
            }.timeInMillis
            userNotificationList.forEach {
                if (it.activeStatus) {
                    if (currentDateTime >= it.triggerDateTimeInMillis) {
                        return@forEach
                    }
                    
                    RemindersManager.startReminder(
                        context = applicationContext,
                        reminderId = it.reminderId,
                        notificationTitle = it.title,
                        notificationMessage = it.message,
                        notificationDeepLink = it.deepLink,
                        notificationType = it.notificationType.typeId,
                        dateTimeMillis = it.triggerDateTimeInMillis,
                    )
                } else {
                    RemindersManager.stopReminder(
                        context = applicationContext,
                        reminderId = it.reminderId,
                    )
                }
            }
        }
    }
    
}