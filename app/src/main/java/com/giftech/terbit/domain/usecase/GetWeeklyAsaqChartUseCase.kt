package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.enums.ProgramTag
import com.giftech.terbit.domain.model.FillOutAsaq
import com.giftech.terbit.domain.model.WeeklyAsaqChart
import com.giftech.terbit.domain.repository.IAsaqResponseRepository
import com.giftech.terbit.domain.repository.IProgramRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetWeeklyAsaqChartUseCase @Inject constructor(
    private val asaqResponseRepository: IAsaqResponseRepository,
    private val programRepository: IProgramRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(
        dayOfWeek: Int,
        week: Int,
    ): Flow<WeeklyAsaqChart> {
        return programRepository.getAll()
            .mapLatest { programList ->
                programList
                    .filterIsInstance(FillOutAsaq::class.java)
                    .filter { it.tag == ProgramTag.WEEKLY_PROGRAM }
                    .filter { it.week == week && it.dayOfWeek == dayOfWeek }
                    .map { it.programId }
            }
            .filterNot {
                it.isEmpty()
            }
            .flatMapLatest { weeklyAsaqProgramIdList ->
                asaqResponseRepository.getAll()
                    .mapLatest { asaqResponseList ->
                        asaqResponseList
                            .filter { it.programId in weeklyAsaqProgramIdList }
                            .sortedBy { it.questionId }
                    }
                    .mapLatest { asaqResponseList ->
                        val xLabels = asaqResponseList.map { "Q${it.questionId}" }
                        val maxFreq =
                            (asaqResponseList.maxOfOrNull { it.freq }?.toDouble() ?: 0.0) / 60
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
            .flowOn(Dispatchers.IO)
    }
    
}