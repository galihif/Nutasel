package com.giftech.terbit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import com.giftech.terbit.notif.RemindersManager.startReminder
import com.giftech.terbit.notif.RemindersManager.stopReminder
import com.giftech.terbit.ui.TerbitApp
import com.giftech.terbit.ui.theme.TerbitTheme
import dagger.hilt.android.AndroidEntryPoint

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
            userNotificationList
                .filter {
                    it.shownStatus.not()
                }
                .forEach {
                    if (it.activeStatus) {
                        if (it.schedulingStatus.not()) {
                            startReminder(
                                context = this,
                                userNotification = it,
                            )
                            viewModel.updateSchedulingStatusUserNotification(it)
                        }
                    } else {
                        stopReminder(
                            context = this,
                            reminderId = it.reminderId,
                        )
                    }
                }
        }
        
        viewModel.getDailyNotificationList().forEach {
            startReminder(
                context = this,
                dailyTipsNotification = it,
            )
        }
    }
    
}