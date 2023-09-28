package com.giftech.terbit.notif

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.giftech.terbit.MainActivity
import com.giftech.terbit.R
import com.giftech.terbit.domain.enums.NotificationType
import com.giftech.terbit.domain.usecase.UpdateShownStatusUserNotificationUseCase
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import javax.inject.Inject

@AndroidEntryPoint
class NotificationAlarmReceiver : BroadcastReceiver() {
    
    @Inject
    lateinit var updateShownStatusUserNotificationUseCase: UpdateShownStatusUserNotificationUseCase
    
    override fun onReceive(context: Context, intent: Intent) = goAsync {
        val reminderId =
            intent.getIntExtra(Constants.NotificationParams.REMINDER_ID, -1)
        val notificationId =
            intent.getIntExtra(Constants.NotificationParams.NOTIFICATION_ID, -1)
        val notificationTitle =
            intent.getStringExtra(Constants.NotificationParams.NOTIFICATION_TITLE).orEmpty()
        val notificationMessage =
            intent.getStringExtra(Constants.NotificationParams.NOTIFICATION_MESSAGE).orEmpty()
        val notificationIdLink =
            intent.getIntExtra(Constants.NotificationParams.NOTIFICATION_ID_LINK, -1)
        val notificationDeepLink =
            intent.getStringExtra(Constants.NotificationParams.NOTIFICATION_DEEP_LINK)
        val notificationDateTimeMillis =
            intent.getLongExtra(Constants.NotificationParams.NOTIFICATION_DATE_TIME_MILLIS, -1)
        val notificationType =
            intent.getStringExtra(Constants.NotificationParams.NOTIFICATION_TYPE).orEmpty().let {
                NotificationType.fromTypeId(it)
            }
        
        if (notificationType == NotificationType.DAILY_TIPS) {
            // Reschedule the reminder (daily-type notification) for tomorrow
            RemindersManager.startReminder(
                applicationContext = context.applicationContext,
                reminderId = reminderId,
                notificationId = notificationId,
                notificationTitle = notificationTitle,
                notificationMessage = notificationMessage,
                notificationDeepLink = notificationDeepLink,
                notificationIdLink = notificationIdLink,
                notificationType = notificationType.typeId,
                dateTimeMillis = Calendar.getInstance().apply {
                    timeInMillis = notificationDateTimeMillis
                    add(Calendar.DAY_OF_MONTH, 1)
                }.timeInMillis,
            )
        } else {
            // Update shown status for user notification
            updateShownStatusUserNotificationUseCase(
                notificationId = notificationId,
                idLink = notificationIdLink,
            )
        }
        
        // Show notification
        val notificationManager = ContextCompat.getSystemService(
            context,
            NotificationManager::class.java,
        ) as NotificationManager
        notificationManager.showNotification(
            applicationContext = context,
            channelId = notificationType.typeId,
            id = reminderId,
            title = notificationTitle,
            message = notificationMessage,
        )
    }
    
    @OptIn(ExperimentalMaterial3Api::class)
    private fun NotificationManager.showNotification(
        applicationContext: Context,
        channelId: String,
        id: Int,
        title: String,
        message: String,
    ) {
        val contentIntent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            id,
            contentIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
        )
        
        val builder = NotificationCompat.Builder(applicationContext, channelId)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_logo_notif_24)
            .setColor(applicationContext.getColor(R.color.purple))
            .setAutoCancel(true)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .apply {
                if (title.isNotEmpty()) setContentTitle(title)
                if (message.isNotEmpty()) {
                    setContentText(message)
                    setStyle(
                        NotificationCompat.BigTextStyle()
                            .bigText(message)
                    )
                }
            }
        
        notify(id, builder.build())
    }
    
}