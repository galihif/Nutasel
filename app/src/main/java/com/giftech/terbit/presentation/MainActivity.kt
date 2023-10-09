package com.giftech.terbit.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.giftech.terbit.presentation.ui.TerbitApp
import com.giftech.terbit.presentation.ui.theme.TerbitTheme
import com.giftech.terbit.presentation.util.RemindersManager.startReminder
import com.giftech.terbit.presentation.util.RemindersManager.stopReminder
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    private val viewModel: MainViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        installSplashScreen()
        
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
                            applicationContext = applicationContext,
                            reminderId = it.reminderId,
                        )
                    }
                }
        }
        
        viewModel.getDailyNotificationList().observe(this) { notificationList ->
            notificationList.forEach {
                startReminder(
                    context = this,
                    dailyTipsNotification = it,
                )
            }
        }
    }
    
}