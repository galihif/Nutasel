package com.giftech.terbit.presentation.service.notificationrestart

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.giftech.terbit.R
import com.giftech.terbit.domain.usecase.GetDailyNotificationListUseCase
import com.giftech.terbit.domain.usecase.GetUnshownUserNotificationListUseCase
import com.giftech.terbit.presentation.util.RemindersManager.startReminder
import com.giftech.terbit.presentation.util.buildHiddenNotification
import com.giftech.terbit.presentation.util.stopNotificationRestartService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@AndroidEntryPoint
class NotificationRestartService : Service() {
    
    private val notificationId = UUID.randomUUID().hashCode()
    
    @Inject
    lateinit var getUnshownUserNotificationListUseCase: GetUnshownUserNotificationListUseCase
    
    @Inject
    lateinit var getDailyNotificationListUseCase: GetDailyNotificationListUseCase
    
    override fun onCreate() {
        super.onCreate()
        startService()
        restartUnshownUserNotification()
        restartDailyTips()
    }
    
    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int,
    ): Int {
        return START_STICKY
    }
    
    private fun startService() {
        startForeground(
            notificationId,
            buildHiddenNotification(
                context = this,
                channelId = getString(R.string.notif_channel_id_systemchannel),
                message = "Configuring...",
            ),
        )
    }
    
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)
    
    private fun restartUnshownUserNotification() {
        scope.launch {
            getUnshownUserNotificationListUseCase().collect { userNotificationList ->
                userNotificationList
                    .forEach {
                        startReminder(
                            context = this@NotificationRestartService,
                            userNotification = it,
                        )
                    }
                stopNotificationRestartService(this@NotificationRestartService)
            }
        }
    }
    
    private fun restartDailyTips() {
        scope.launch {
            getDailyNotificationListUseCase().collect { notificationList ->
                notificationList.forEach {
                    startReminder(
                        context = this@NotificationRestartService,
                        dailyTipsNotification = it,
                    )
                }
            }
        }
    }
    
    override fun onBind(intent: Intent): IBinder? {
        return null
    }
    
    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
    
}