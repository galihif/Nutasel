package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.enums.ProgramTag
import com.giftech.terbit.domain.enums.SedenterType
import com.giftech.terbit.domain.model.Summary
import com.giftech.terbit.domain.repository.IProgramRepository
import com.giftech.terbit.domain.repository.IUserRepository
import com.giftech.terbit.domain.util.Constants
import com.giftech.terbit.domain.util.percentageOf
import com.giftech.terbit.domain.util.toLocalDateTime
import com.giftech.terbit.domain.util.toSinglePrecision
import com.giftech.terbit.domain.util.toString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class GetSummaryUseCase @Inject constructor(
    private val userRepository: IUserRepository,
    private val programRepository: IProgramRepository,
) {
    
    operator fun invoke(): Flow<Summary> {
        return userRepository.getUser().zip(
            programRepository.getAll()
        ) { user, programList ->
            val weeklyProgramList = programList.filter {
                it.tag == ProgramTag.WEEKLY_PROGRAM
            }.sortedWith(
                compareBy(
                    { it.week },
                    { it.dayOfWeek },
                )
            )
            
            val lastCompletedProgram = weeklyProgramList.lastOrNull { it.isComplete }
            val lastCompletedWeek = lastCompletedProgram?.week
            val lastCompletedDayOfWeek = lastCompletedProgram?.dayOfWeek
            val nextDayProgramList = programList.filter {
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
            
            val userName = user.nama
            val bmiCategory = user.kategoriIMT.title
            val monitoringLevel = SedenterType.RINGAN.title // TODO: Replace with real data
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
            val isPostTestDone = programList.all { it.tag == ProgramTag.POST_TEST && it.isComplete }
            val isAllWeeklyProgramDone = weeklyProgramList.all { it.isComplete }
            val totalProgram = weeklyProgramList.size
            val totalCompletedProgram = weeklyProgramList.count { it.isComplete }
            val programProgressPercentage = percentageOf(totalCompletedProgram, totalProgram)
            val totalCompletedDaysInWeek = lastCompletedProgram?.dayOfWeek ?: 0
            val currentWeek = lastCompletedProgram?.week ?: 1
            val totalCompletedWeek = lastCompletedProgram?.week?.minus(1) ?: 0
            val totalWeek = weeklyProgramList.lastOrNull()?.week ?: 0
            
            Summary(
                userName = userName,
                bmiCategory = bmiCategory,
                monitoringLevel = monitoringLevel,
                bmiValue = bmiValue,
                postTestOpeningDate = postTestOpeningDate,
                isPostTestAvailable = isPostTestAvailable,
                isPostTestDone = isPostTestDone,
                isAllWeeklyProgramDone = isAllWeeklyProgramDone,
                nextDayProgramList = nextDayProgramList,
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