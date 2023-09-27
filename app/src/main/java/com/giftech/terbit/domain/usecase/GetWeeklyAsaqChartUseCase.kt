package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.enums.ProgramTag
import com.giftech.terbit.domain.model.FillOutAsaq
import com.giftech.terbit.domain.model.WeeklyAsaqChart
import com.giftech.terbit.domain.repository.IAsaqResponseRepository
import com.giftech.terbit.domain.repository.IProgramRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetWeeklyAsaqChartUseCase @Inject constructor(
    private val asaqResponseRepository: IAsaqResponseRepository,
    private val programRepository: IProgramRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(
        dayOfWeek: Int,
        week: Int,
    ): Flow<WeeklyAsaqChart> {
        return programRepository.getAll().mapLatest { programList ->
            programList
                .filterIsInstance(FillOutAsaq::class.java)
                .filter { it.tag == ProgramTag.WEEKLY_PROGRAM }
                .filter { it.week == week && it.dayOfWeek == dayOfWeek }
                .map { it.programId }
        }.flatMapLatest { weeklyAsaqProgramIdList ->
            asaqResponseRepository.getAll().mapLatest { asaqResponseList ->
                asaqResponseList
                    .filter { weeklyAsaqProgramIdList.contains(it.programId) }
                    .sortedBy { it.questionId }
            }.mapLatest { asaqResponseList ->
                val xLabels = asaqResponseList.map { "Q${it.questionId}" }
                val maxFreq = asaqResponseList.maxOf { it.freq }.toDouble() / 60
                val maxY = when {
                    maxFreq <= 4.0 -> 4
                    maxFreq <= 8.0 -> 8
                    maxFreq <= 12.0 -> 12
                    maxFreq <= 16.0 -> 16
                    maxFreq <= 20.0 -> 20
                    else -> 24
                }
                val yLabelCount = 5
                
                WeeklyAsaqChart(
                    entry = asaqResponseList,
                    xLabels = xLabels,
                    maxY = maxY,
                    yLabelCount = yLabelCount,
                )
            }
        }
    }
    
}