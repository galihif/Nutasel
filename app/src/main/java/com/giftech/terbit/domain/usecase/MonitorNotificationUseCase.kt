package com.giftech.terbit.domain.usecase

import android.util.Log
import com.giftech.terbit.domain.enums.ProgramTag
import com.giftech.terbit.domain.model.FillOutAsaq
import com.giftech.terbit.domain.repository.INotificationRepository
import com.giftech.terbit.domain.repository.IProgramRepository
import com.giftech.terbit.domain.repository.IUserNotificationRepository
import com.giftech.terbit.domain.util.toLocalDateTime
import com.giftech.terbit.domain.util.toMillis
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import java.time.LocalDateTime
import javax.inject.Inject

class MonitorNotificationUseCase @Inject constructor(
    private val programRepository: IProgramRepository,
    private val notificationRepository: INotificationRepository,
    private val userNotificationRepository: IUserNotificationRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(): Flow<Unit> {
        val notificationList = notificationRepository.getAll()
        return programRepository.getAll()
            .mapLatest { programList ->
                programList.sortedWith(
                    compareBy(
                        { it.week },
                        { it.dayOfWeek },
                    )
                )
            }
            .map { programList ->
                val weeklyProgramList = programList.filter {
                    it.tag == ProgramTag.WEEKLY_PROGRAM
                }
                
                // TODO: Ganti pakai all
                val isPreTestDone = programList.any {
                    it.tag == ProgramTag.PRE_TEST && it.isCompleted
                }
                val isWeeklyProgramDone = weeklyProgramList.all {
                    it.isCompleted
                }
                val isPostTestDone = programList.all {
                    it.tag == ProgramTag.POST_TEST && it.isCompleted
                }
                
                Log.d("MonitorNotification", "isPreTestDone: $isPreTestDone")
                if (isPostTestDone || isPreTestDone.not()) return@map
                
                val currentDate = LocalDateTime.now()
                val preTestDate = programList.first {
                    it.tag == ProgramTag.PRE_TEST && it.isCompleted
                }.completionDateInMillis.toLocalDateTime()
                val day1Date = preTestDate.plusDays(7)
                
                if (isWeeklyProgramDone.not()) {
                    // ID 1000
                    val notification1 = notificationList.first { it.id == 1000 }
                    val day7Date = day1Date.plusDays(6)
                    weeklyProgramList.forEach {
                        // TODO: Deep link baca artikel
                        val idLink = it.programId
                        if (it.isCompleted) {
                            userNotificationRepository.updateActiveStatus(
                                notificationId = notification1.id,
                                idLink = idLink,
                                activeStatus = false,
                            )
                        } else {
                            val deepLink = when (it) {
                                is FillOutAsaq -> "https://terbiasafit.com/program/weekly_asaq/$idLink"
                                else -> null
                            }
                            userNotificationRepository.insert(
                                notificationId = notification1.id,
                                title = notification1.title,
                                message = notification1.message.replace(
                                    "{{day_of_week}}",
                                    it.dayOfWeek.toString()
                                ),
                                deepLink = deepLink,
                                idLink = idLink,
                                readStatus = false,
                                triggerDateTimeInMillis = day7Date.withHour(notification1.triggerHours)
                                    .withMinute(notification1.triggerMinutes).toMillis(),
                                notificationType = notification1.type.typeId,
                                activeStatus = true,
                            )
                        }
                    }
                    
                    // ID 2000
                    if (preTestDate == currentDate) {
                        val notification2 = notificationList.first { it.id == 2000 }
                        userNotificationRepository.insert(
                            notificationId = notification2.id,
                            title = notification2.title,
                            message = notification2.message.replace(
                                "{{dd-mm-yyyy}}",
                                preTestDate.toLocalDate().toString()
                            ),
                            deepLink = null,
                            idLink = 0,
                            readStatus = false,
                            triggerDateTimeInMillis = LocalDateTime.now().toMillis(),
                            notificationType = notification2.type.typeId,
                            activeStatus = true,
                        )
                    }
                    
                    // ID 3000
                    if (currentDate >= preTestDate && currentDate <= day7Date) {
                        val notification3 = notificationList.first { it.id == 3000 }
                        userNotificationRepository.insert(
                            notificationId = notification3.id,
                            title = notification3.title,
                            message = notification3.message,
                            deepLink = null,
                            idLink = 0,
                            readStatus = false,
                            triggerDateTimeInMillis = day1Date.withHour(notification3.triggerHours)
                                .withMinute(notification3.triggerMinutes).toMillis(),
                            notificationType = notification3.type.typeId,
                            activeStatus = true,
                        )
                    }
                }
                
                if (isWeeklyProgramDone) {
                    val lastWeeklyProgram = weeklyProgramList.last()
                    val lastDay = day1Date
                        .plusWeeks(lastWeeklyProgram.week!!.minus(1).toLong())
                        .plusDays(lastWeeklyProgram.dayOfWeek!!.minus(1).toLong())
                    
                    // ID 4000
                    val notification4 = notificationList.first { it.id == 4000 }
                    userNotificationRepository.insert(
                        notificationId = notification4.id,
                        title = notification4.title,
                        message = notification4.message.replace(
                            "{{dd-mm-yyyy}}",
                            lastDay.toLocalDate().toString()
                        ),
                        deepLink = null,
                        idLink = 0,
                        readStatus = false,
                        triggerDateTimeInMillis = LocalDateTime.now().toMillis(),
                        notificationType = notification4.type.typeId,
                        activeStatus = true,
                    )
                    
                    // ID 5000
                    if (currentDate <= lastDay.plusDays(7)) {
                        val notification5 = notificationList.first { it.id == 5000 }
                        // TODO: Deep link post test
                        val deepLink = null
                        userNotificationRepository.insert(
                            notificationId = notification5.id,
                            title = notification5.title,
                            message = notification5.message,
                            deepLink = deepLink,
                            idLink = 0,
                            readStatus = false,
                            triggerDateTimeInMillis = lastDay.withHour(notification5.triggerHours)
                                .withMinute(notification5.triggerMinutes).toMillis(),
                            notificationType = notification5.type.typeId,
                            activeStatus = true,
                        )
                    }
                }
            }
    }
    
}