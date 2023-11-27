package com.giftech.terbit.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.giftech.terbit.presentation.ui.TerbitApp
import com.giftech.terbit.presentation.ui.theme.TerbitTheme
import com.giftech.terbit.presentation.util.RemindersManager.startReminder
import com.giftech.terbit.presentation.util.RemindersManager.stopReminder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
        lifecycleScope.launch {
            viewModel.getAllUserNotification().observe(this@MainActivity) { userNotificationList ->
                userNotificationList
                    .forEach {
                        if (it.activeStatus) {
                            if (it.schedulingStatus.not()) {
                                startReminder(
                                    context = this@MainActivity,
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
        }
        
        lifecycleScope.launch {
            viewModel.getDailyNotificationList().observe(this@MainActivity) { notificationList ->
                notificationList.forEach {
                    startReminder(
                        context = this@MainActivity,
                        dailyTipsNotification = it,
                    )
                }
            }
        }
    }
    
}