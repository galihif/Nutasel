package com.giftech.terbit.domain.usecase

import com.giftech.terbit.data.repository.UserNotificationRepository
import com.giftech.terbit.domain.enums.ProgramTag
import com.giftech.terbit.domain.model.HomeSummary
import com.giftech.terbit.domain.model.Program
import com.giftech.terbit.domain.repository.IAsaqRepository
import com.giftech.terbit.domain.repository.IProgramRepository
import com.giftech.terbit.domain.repository.IUserRepository
import com.giftech.terbit.domain.util.Constants
import com.giftech.terbit.domain.util.percentageOf
import com.giftech.terbit.domain.util.toLocalDateTime
import com.giftech.terbit.domain.util.toSinglePrecision
import com.giftech.terbit.domain.util.toString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import java.time.LocalDate
import javax.inject.Inject

class GetHomeSummaryUseCase @Inject constructor(
    private val userRepository: IUserRepository,
    private val programRepository: IProgramRepository,
    private val asaqRepository: IAsaqRepository,
    private val userNotificationRepository: UserNotificationRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(): Flow<HomeSummary> {
        return userRepository.getUser().flatMapLatest { user ->
            asaqRepository.getSedenterType().flatMapLatest { sedenterType ->
                programRepository.getAll().flatMapLatest { programList ->
                    userNotificationRepository.getAll().mapLatest { userNotification ->
                        val weeklyProgramList = programList.filter {
                            it.tag == ProgramTag.WEEKLY_PROGRAM
                        }.sortedWith(
                            compareBy(
                                { it.week },
                                { it.dayOfWeek },
                            )
                        )
                        
                        // The weekly program opens after 3 days of pre-test
                        val programFirstDayDate = programList
                            .first { it.tag == ProgramTag.PRE_TEST }
                            .completionDateInMillis.toLocalDateTime().toLocalDate()
                            .plusDays(Constants.BreakDays.AFTER_PRE_TEST)
                        val isWeeklyProgramAvailable = LocalDate.now() >= programFirstDayDate
                        
                        val lastCompletedProgram = weeklyProgramList
                            .lastOrNull { it.isCompleted }
                        var nextDayProgramList = emptyList<Program>()
                        var isNextDayProgramAvailable = false
                        if (isWeeklyProgramAvailable) {
                            val lastCompletedWeek = lastCompletedProgram?.week
                            val lastCompletedDayOfWeek = lastCompletedProgram?.dayOfWeek
                            
                            nextDayProgramList = when {
                                lastCompletedProgram == null -> {
                                    weeklyProgramList.filter { it.week == 1 && it.dayOfWeek == 1 }
                                }
                                
                                weeklyProgramList.any {
                                    it.week == lastCompletedWeek &&
                                            it.dayOfWeek == lastCompletedDayOfWeek &&
                                            !it.isCompleted
                                } -> {
                                    weeklyProgramList.filter {
                                        it.week == lastCompletedWeek
                                                && it.dayOfWeek == lastCompletedDayOfWeek
                                                && !it.isCompleted
                                    }
                                }
                                
                                lastCompletedDayOfWeek == 7 -> {
                                    weeklyProgramList.filter {
                                        it.week == lastCompletedWeek!! + 1 &&
                                                it.dayOfWeek == 1
                                    }
                                }
                                
                                else -> {
                                    weeklyProgramList.filter {
                                        it.week == lastCompletedWeek &&
                                                it.dayOfWeek == lastCompletedDayOfWeek!! + 1
                                    }
                                }
                            }
                            
                            if (nextDayProgramList.isNotEmpty()) {
                                val nextDayDate = programFirstDayDate.plusDays(
                                    nextDayProgramList.first().let {
                                        (it.week!! - 1) * 7 + (it.dayOfWeek!! - 1)
                                    }.toLong()
                                )
                                isNextDayProgramAvailable = LocalDate.now() >= nextDayDate
                            }
                        }
                        
                        val userName = user.nama
                        val bmiCategory = user.kategoriIMT.title
                        val monitoringLevel = sedenterType.title
                        val bmiValue = user.skorIMT.toSinglePrecision()
                        
                        val weeklyProgramTotalDays = ((weeklyProgramList.last().week!! - 1) * 7 +
                                weeklyProgramList.last().dayOfWeek!!).toLong()
                        val preTestCompletionDate = programList
                            .last { it.tag == ProgramTag.PRE_TEST }
                            .completionDateInMillis.toLocalDateTime().toLocalDate()
                        
                        val postTestOpeningDate = preTestCompletionDate
                            .plusDays(Constants.BreakDays.AFTER_PRE_TEST)
                            // Intersects with 3 days after the pre-test:
                            // first day (day 1) of weekly program is 3 days after pre-test
                            .plusDays(-1 + weeklyProgramTotalDays)
                            .plusDays(Constants.BreakDays.BEFORE_POST_TEST)
                        val postTestOpeningDateString = postTestOpeningDate
                            .toString(Constants.DatePattern.READABLE_DEFAULT)
                        val isPostTestDone = programList
                            .filter { it.tag == ProgramTag.POST_TEST }
                            .all { it.isCompleted }
                        val isAllWeeklyProgramDone = weeklyProgramList.all { it.isCompleted }
                        val isPostTestAvailable = isAllWeeklyProgramDone &&
                                LocalDate.now() >= postTestOpeningDate
                        
                        val totalProgram = weeklyProgramList.size
                        val totalCompletedProgram = weeklyProgramList.count { it.isCompleted }
                        val programProgressPercentage =
                            percentageOf(
                                totalCompletedProgram,
                                totalProgram
                            )
                        val totalCompletedDaysInWeek = lastCompletedProgram?.dayOfWeek ?: 0
                        val currentWeek = lastCompletedProgram?.week ?: 1
                        val totalCompletedWeek = lastCompletedProgram?.week?.minus(
                            if (lastCompletedProgram.dayOfWeek == 7) 0
                            else 1
                        ) ?: 0
                        val totalWeek = weeklyProgramList.lastOrNull()?.week ?: 0
                        
                        val isNotificationEmpty = userNotification.none {
                            it.activeStatus && it.shownStatus
                        }
                        
                        HomeSummary(
                            userName = userName,
                            bmiCategory = bmiCategory,
                            monitoringLevel = monitoringLevel,
                            bmiValue = bmiValue,
                            postTestOpeningDate = postTestOpeningDateString,
                            isPostTestAvailable = isPostTestAvailable,
                            isPostTestDone = isPostTestDone,
                            isAllWeeklyProgramDone = isAllWeeklyProgramDone,
                            nextDayProgramList = nextDayProgramList,
                            isNextDayProgramAvailable = isNextDayProgramAvailable,
                            totalProgram = totalProgram,
                            totalCompletedProgram = totalCompletedProgram,
                            programProgressPercentage = programProgressPercentage,
                            totalCompletedDaysInWeek = totalCompletedDaysInWeek,
                            currentWeek = currentWeek,
                            totalCompletedWeek = totalCompletedWeek,
                            totalWeek = totalWeek,
                            isNotificationEmpty = isNotificationEmpty,
                        )
                    }
                }
            }
        }
            .flowOn(Dispatchers.IO)
    }
    
}