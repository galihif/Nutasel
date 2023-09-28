package com.giftech.terbit.notif

import android.app.Notification
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.giftech.terbit.R
import com.giftech.terbit.domain.usecase.GetDailyNotificationListUseCase
import com.giftech.terbit.domain.usecase.GetUnshownUserNotificationListUseCase
import com.giftech.terbit.notif.RemindersManager.startReminder
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
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }
    
    private fun startService() {
        startForeground(
            notificationId,
            createNotification(),
        )
    }
    
    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, getString(R.string.notif_channel_id_systemchannel))
            .setContentText("Configuring...")
            .setSmallIcon(R.drawable.ic_logo_notif_24)
            .setColor(getColor(R.color.purple))
            .setSilent(true)
            .setVibrate(null)
            .setOngoing(true)
            .setVisibility(NotificationCompat.VISIBILITY_SECRET)
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .build()
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
                stopAlarmRebootService()
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

fun Context.startAlarmRebootService() {
    Intent(this, NotificationRestartService::class.java)
        .also { intent -> startService(this, intent) }
}

fun Context.stopAlarmRebootService() {
    Intent(this, NotificationRestartService::class.java)
        .also { intent -> stopService(intent) }
}