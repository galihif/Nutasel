package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.enums.ProgramTag
import com.giftech.terbit.domain.model.MonitoringSummary
import com.giftech.terbit.domain.repository.IProgramRepository
import com.giftech.terbit.domain.util.Constants
import com.giftech.terbit.domain.util.toLocalDateTime
import com.giftech.terbit.domain.util.toString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import java.time.LocalDate
import javax.inject.Inject

class GetMonitoringSummaryUseCase @Inject constructor(
    private val programRepository: IProgramRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(): Flow<MonitoringSummary> {
        return programRepository.getAll()
            .mapLatest { programList ->
                val weeklyProgramList = programList.filter {
                    it.tag == ProgramTag.WEEKLY_PROGRAM
                }.sortedWith(
                    compareBy(
                        { it.week },
                        { it.dayOfWeek },
                    )
                )
                
                val firstDayDate = programList
                    .first { it.tag == ProgramTag.PRE_TEST }
                    .completionDateInMillis.toLocalDateTime().toLocalDate()
                    .plusDays(Constants.BreakDays.AFTER_PRE_TEST)
                val completedDayList = weeklyProgramList
                    .filter { it.isCompleted }
                    .map {
                        val programDay = if (it.week == 1) {
                            it.dayOfWeek!!
                        } else {
                            it.dayOfWeek!! + (it.week!! - 1) * 7
                        }.toLong()
                        firstDayDate.plusDays(programDay - 1)
                    }
                    .distinct()
                
                val weeklyProgramOpeningDate = firstDayDate
                    .toString(Constants.DatePattern.READABLE_DEFAULT)
                val isWeeklyProgramAvailable = LocalDate.now() >= firstDayDate
                
                MonitoringSummary(
                    completedDayList = completedDayList,
                    weeklyProgramOpeningDate = weeklyProgramOpeningDate,
                    isWeeklyProgramAvailable = isWeeklyProgramAvailable,
                    weeklyProgramList = weeklyProgramList.groupBy { it.week!! },
                )
            }
            .flowOn(Dispatchers.IO)
    }
    
}