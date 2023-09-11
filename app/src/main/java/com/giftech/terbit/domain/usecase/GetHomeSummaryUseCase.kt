package com.giftech.terbit.domain.usecase

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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import java.time.LocalDate
import javax.inject.Inject

class GetHomeSummaryUseCase @Inject constructor(
    private val userRepository: IUserRepository,
    private val programRepository: IProgramRepository,
    private val asaqRepository: IAsaqRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(): Flow<HomeSummary> {
        return userRepository.getUser().flatMapLatest { user ->
            asaqRepository.getSedenterType().flatMapLatest { sedenterType ->
                programRepository.getAll().mapLatest { programList ->
                    val weeklyProgramList = programList.filter {
                        it.tag == ProgramTag.WEEKLY_PROGRAM
                    }.sortedWith(
                        compareBy(
                            { it.week },
                            { it.dayOfWeek },
                        )
                    )
                    
                    // The weekly program opens after 7 days of pre-test
                    val programFirstDayDate = programList
                        .last { it.tag == ProgramTag.PRE_TEST }
                        .completionDateInMillis.toLocalDateTime().toLocalDate()
                        .plusDays(7)
                    val isWeeklyProgramAvailable = LocalDate.now() >= programFirstDayDate
                    
                    val lastCompletedProgram = weeklyProgramList.lastOrNull { it.isComplete }
                    var nextDayProgramList = emptyList<Program>()
                    var isNextDayProgramAvailable = false
                    if (isWeeklyProgramAvailable) {
                        val lastCompletedWeek = lastCompletedProgram?.week
                        val lastCompletedDayOfWeek = lastCompletedProgram?.dayOfWeek
                        nextDayProgramList = programList.filter {
                            if (lastCompletedProgram == null) {
                                it.week == 1 && it.dayOfWeek == 1
                            } else {
                                if (lastCompletedDayOfWeek == 7) {
                                    it.week == lastCompletedWeek!! + 1 && it.dayOfWeek == 1
                                } else {
                                    it.week == lastCompletedWeek && it.dayOfWeek == lastCompletedDayOfWeek!! + 1
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
                    val breakDayAfterPreTest = 7L
                    val breakDayBeforePostTest = 7L
                    val preTestCompletionDate = programList.last { it.tag == ProgramTag.PRE_TEST }
                        .completionDateInMillis.toLocalDateTime().toLocalDate()
                    
                    val postTestOpeningDate = preTestCompletionDate
                        .plusDays(breakDayAfterPreTest)
                        // Intersects with 7 days after the pre-test
                        // First day (day one) of weekly program is 7 days after pre-test
                        .plusDays(-1 + weeklyProgramTotalDays)
                        .plusDays(breakDayBeforePostTest)
                        .toString(Constants.DatePattern.READABLE_DEFAULT)
                    
                    val isPostTestAvailable = weeklyProgramList.all { it.isComplete }
                    val isPostTestDone =
                        programList.all { it.tag == ProgramTag.POST_TEST && it.isComplete }
                    val isAllWeeklyProgramDone = weeklyProgramList.all { it.isComplete }
                    val totalProgram = weeklyProgramList.size
                    val totalCompletedProgram = weeklyProgramList.count { it.isComplete }
                    val programProgressPercentage =
                        percentageOf(totalCompletedProgram, totalProgram)
                    val totalCompletedDaysInWeek = lastCompletedProgram?.dayOfWeek ?: 0
                    val currentWeek = lastCompletedProgram?.week ?: 1
                    val totalCompletedWeek = lastCompletedProgram?.week?.minus(1) ?: 0
                    val totalWeek = weeklyProgramList.lastOrNull()?.week ?: 0
                    
                    HomeSummary(
                        userName = userName,
                        bmiCategory = bmiCategory,
                        monitoringLevel = monitoringLevel,
                        bmiValue = bmiValue,
                        postTestOpeningDate = postTestOpeningDate,
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
                    )
                }
            }
        }
    }
    
}