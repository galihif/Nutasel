package com.giftech.terbit.data.source.local.statics

import com.giftech.terbit.data.source.local.statics.model.NotificationEntity
import com.giftech.terbit.domain.enums.NotificationType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationData @Inject constructor() {
    
    val notificationList = listOf(
        NotificationEntity(
            id = 1000,
            title = "",
            message = "Pantau sedentari hari ke {{day_of_week}} menunggu untuk dikerjakan",
            triggerTime = "08:00",
            type = NotificationType.PROGRAM_REMINDER.typeId,
        ),
        NotificationEntity(
            id = 2000,
            title = "",
            message = "Program kamu akan dibuka pada {{dd_mm_yyyy}}",
            triggerTime = null,
            type = NotificationType.PROGRAM_REMINDER.typeId,
        ),
        NotificationEntity(
            id = 3000,
            title = "",
            message = "Program kamu sudah berjalan nih, ayo dikerjain",
            triggerTime = "08:00",
            type = NotificationType.PROGRAM_REMINDER.typeId,
        ),
        NotificationEntity(
            id = 4000,
            title = "",
            message = "Post test kamu akan dibuka pada {{dd_mm_yyyy}}",
            triggerTime = null,
            type = NotificationType.PROGRAM_REMINDER.typeId,
        ),
        NotificationEntity(
            id = 5000,
            title = "",
            message = "Post test kamu sudah dibuka nih yuk diisi",
            triggerTime = "08:00",
            type = NotificationType.PROGRAM_REMINDER.typeId,
        ),
        NotificationEntity(
            id = 6000,
            title = "Yuk isi pemantauan hari ini!",
            message = "Pantau terus aktivitas dan jaga kesehatanmu dengan mudah dan menyenangkan, semangat sehat ",
            triggerTime = "17:00",
            type = NotificationType.DAILY_TIPS.typeId,
        ),
        NotificationEntity(
            id = 7000,
            title = "Waktunya makan sehat \uD83C\uDF4E",
            message = "Jangan lupa, hidup dimulai dari pilihan makanan yang tepat, jaga kesehatanmu dan tetap semangat",
            triggerTime = "06:15",
            type = NotificationType.DAILY_TIPS.typeId,
        ),
        NotificationEntity(
            id = 8000,
            title = "Yuk siapkan tubuhmu untuk istirahat \uD83D\uDE34",
            message = "Ingat sobat, tidur yang cukup adalah kunci kesehatan dan kinerja yang optimal, yuk cukupi kebutuhan tidur kamu",
            triggerTime = "09:00",
            type = NotificationType.DAILY_TIPS.typeId,
        ),
        NotificationEntity(
            id = 9000,
            title = "Yuk terus bergerak dan aktif \uD83D\uDC5F",
            message = "Jangan biarkan rutinitas menyebabkan kebiasaan sedentari. Jadikan gerakan dan aktivitas sebagai bagian penting dalam gaya hidup sehatmu!",
            triggerTime = "12:30",
            type = NotificationType.DAILY_TIPS.typeId,
        ),
    )
    
}