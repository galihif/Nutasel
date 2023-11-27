package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.enums.ProgramTag
import com.giftech.terbit.domain.model.FillOutAsaq
import com.giftech.terbit.domain.model.Program
import com.giftech.terbit.domain.repository.INotificationRepository
import com.giftech.terbit.domain.repository.IProgramRepository
import com.giftech.terbit.domain.repository.IUserNotificationRepository
import com.giftech.terbit.domain.util.Constants
import com.giftech.terbit.domain.util.toLocalDateTime
import com.giftech.terbit.domain.util.toMillis
import com.giftech.terbit.domain.util.toString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
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
        val defaultReadStatus = false
        val defaultSchedulingStatus = false
        val defaultShownStatus = false
        
        return notificationRepository.getAll()
            .flatMapLatest { notificationList ->
                programRepository.getAll()
                    .mapLatest { programList ->
                        programList.sortedWith(
                            compareBy(
                                { it.week },
                                { it.dayOfWeek },
                            )
                        )
                    }
                    .mapLatest { programList ->
                        val preTestProgramList = mutableListOf<Program>()
                        val weeklyProgramList = mutableListOf<Program>()
                        val postTestProgramList = mutableListOf<Program>()
                        
                        programList.forEach {
                            when (it.tag) {
                                ProgramTag.PRE_TEST -> preTestProgramList.add(it)
                                ProgramTag.WEEKLY_PROGRAM -> weeklyProgramList.add(it)
                                ProgramTag.POST_TEST -> postTestProgramList.add(it)
                            }
                        }
                        
                        Triple(preTestProgramList, weeklyProgramList, postTestProgramList)
                    }
                    .filter { (preTestProgramList, _, _) ->
                        val isPreTestDone = preTestProgramList.all { it.isCompleted }
                        isPreTestDone
                    }
                    .mapLatest { (preTestProgramList, weeklyProgramList, postTestProgramList) ->
                        val isWeeklyProgramDone = weeklyProgramList.all { it.isCompleted }
                        val isPostTestDone = postTestProgramList.all { it.isCompleted }
                        
                        val currentDate = LocalDateTime.now()
                        val preTestDate =
                            preTestProgramList.first().completionDateInMillis.toLocalDateTime()
                        val day1Date = preTestDate.plusDays(Constants.BreakDays.AFTER_PRE_TEST)
                        
                        val isWeeklyProgramStarted = currentDate.toLocalDate() >=
                                day1Date.toLocalDate()
                        
                        // Not using isWeeklyProgramDone.not()
                        // because we need to disable the last day notification
                        if (isPostTestDone.not() && isWeeklyProgramStarted) {
                            // ID 1000
                            val notification1 = notificationList.first { it.id == 1000 }
                            val triggerDateTime = day1Date
                                .withHour(notification1.triggerHours)
                                .withMinute(notification1.triggerMinutes)
                            val weeklyAsaqProgramList = weeklyProgramList
                                .filterIsInstance<FillOutAsaq>()
                                .groupBy { it.week!! }
                            for (i in weeklyProgramList.first().week!!..weeklyProgramList.last().week!!) {
                                val triggerDateTimeMillis = triggerDateTime
                                    .plusDays((7L * i) - 1)
                                    .toMillis()
                                weeklyAsaqProgramList[i]?.forEach {
                                    val idLink = it.programId
                                    if (it.isCompleted) {
                                        userNotificationRepository.updateActiveStatus(
                                            notificationId = notification1.id,
                                            idLink = idLink,
                                            activeStatus = false,
                                        )
                                    } else {
                                        val deepLink =
                                            "https://terbiasafit.com/program/weekly_asaq/$idLink"
                                        userNotificationRepository.insert(
                                            notificationId = notification1.id,
                                            title = notification1.title,
                                            message = notification1.message.replace(
                                                "{{day_of_week}}",
                                                it.dayOfWeek.toString()
                                            ),
                                            deepLink = deepLink,
                                            idLink = idLink,
                                            readStatus = defaultReadStatus,
                                            triggerDateTimeInMillis = triggerDateTimeMillis,
                                            notificationType = notification1.type.typeId,
                                            activeStatus = true,
                                            schedulingStatus = defaultSchedulingStatus,
                                            shownStatus = defaultShownStatus,
                                        )
                                    }
                                }
                            }
                        }
                        
                        if (isWeeklyProgramDone.not()) {
                            // ID 2000
                            if (currentDate.toLocalDate() >= preTestDate.toLocalDate() && currentDate.toLocalDate() < day1Date.toLocalDate()) {
                                val notification2 = notificationList.first { it.id == 2000 }
                                userNotificationRepository.insert(
                                    notificationId = notification2.id,
                                    title = notification2.title,
                                    message = notification2.message.replace(
                                        "{{dd_mm_yyyy}}",
                                        day1Date.toLocalDate()
                                            .toString(Constants.DatePattern.READABLE_DEFAULT)
                                    ),
                                    deepLink = null,
                                    idLink = 0,
                                    readStatus = defaultReadStatus,
                                    triggerDateTimeInMillis = currentDate.toMillis(),
                                    notificationType = notification2.type.typeId,
                                    activeStatus = true,
                                    schedulingStatus = defaultSchedulingStatus,
                                    shownStatus = defaultShownStatus,
                                )
                            } else if (currentDate.toLocalDate() >= day1Date.toLocalDate()) {
                                userNotificationRepository.updateActiveStatus(
                                    notificationId = 2000,
                                    idLink = 0,
                                    activeStatus = false,
                                )
                            }
                            
                            // ID 3000
                            val isDay1Done = weeklyProgramList.any {
                                it.week == 1 && it.dayOfWeek == 1 && it.isCompleted
                            }
                            val day7Date = day1Date.plusDays(6)
                            if (currentDate.toLocalDate() >= preTestDate.toLocalDate() &&
                                currentDate.toLocalDate() < day7Date.toLocalDate() &&
                                isDay1Done.not()
                            ) {
                                val notification3 = notificationList.first { it.id == 3000 }
                                userNotificationRepository.insert(
                                    notificationId = notification3.id,
                                    title = notification3.title,
                                    message = notification3.message,
                                    deepLink = null,
                                    idLink = 0,
                                    readStatus = defaultReadStatus,
                                    triggerDateTimeInMillis = day1Date
                                        .withHour(notification3.triggerHours)
                                        .withMinute(notification3.triggerMinutes)
                                        .toMillis(),
                                    notificationType = notification3.type.typeId,
                                    activeStatus = true,
                                    schedulingStatus = defaultSchedulingStatus,
                                    shownStatus = defaultShownStatus,
                                )
                            } else {
                                userNotificationRepository.updateActiveStatus(
                                    notificationId = 3000,
                                    idLink = 0,
                                    activeStatus = false,
                                )
                            }
                        }
                        
                        if (isWeeklyProgramDone && isPostTestDone.not()) {
                            val lastWeeklyProgram = weeklyProgramList.last()
                            val lastDayDate = day1Date
                                .plusWeeks(lastWeeklyProgram.week!!.minus(1).toLong())
                                .plusDays(lastWeeklyProgram.dayOfWeek!!.minus(1).toLong())
                            val postTestDate = lastDayDate
                                .plusDays(Constants.BreakDays.BEFORE_POST_TEST)
                            
                            // ID 4000
                            if (currentDate < postTestDate) {
                                val notification4 = notificationList.first { it.id == 4000 }
                                userNotificationRepository.insert(
                                    notificationId = notification4.id,
                                    title = notification4.title,
                                    message = notification4.message.replace(
                                        "{{dd_mm_yyyy}}",
                                        postTestDate.toLocalDate()
                                            .toString(Constants.DatePattern.READABLE_DEFAULT)
                                    ),
                                    deepLink = null,
                                    idLink = 0,
                                    readStatus = defaultReadStatus,
                                    triggerDateTimeInMillis = currentDate.toMillis(),
                                    notificationType = notification4.type.typeId,
                                    activeStatus = true,
                                    schedulingStatus = defaultSchedulingStatus,
                                    shownStatus = defaultShownStatus,
                                )
                            } else {
                                userNotificationRepository.updateActiveStatus(
                                    notificationId = 4000,
                                    idLink = 0,
                                    activeStatus = false,
                                )
                            }
                            
                            // ID 5000
                            val notification5 = notificationList.first { it.id == 5000 }
                            val idLink = Constants.ProgramId.LAST_ASAQ
                            val deepLink =
                                "https://terbiasafit.com/program/preposttest_asaq/1/$idLink"
                            userNotificationRepository.insert(
                                notificationId = notification5.id,
                                title = notification5.title,
                                message = notification5.message,
                                deepLink = deepLink,
                                idLink = idLink,
                                readStatus = defaultReadStatus,
                                triggerDateTimeInMillis = postTestDate
                                    .withHour(notification5.triggerHours)
                                    .withMinute(notification5.triggerMinutes)
                                    .toMillis(),
                                notificationType = notification5.type.typeId,
                                activeStatus = true,
                                schedulingStatus = defaultSchedulingStatus,
                                shownStatus = defaultShownStatus,
                            )
                        }
                        
                        if (isPostTestDone) {
                            userNotificationRepository.updateActiveStatus(
                                notificationId = 5000,
                                idLink = Constants.ProgramId.LAST_ASAQ,
                                activeStatus = false,
                            )
                        }
                    }
            }
            .flowOn(Dispatchers.IO)
    }
    
}