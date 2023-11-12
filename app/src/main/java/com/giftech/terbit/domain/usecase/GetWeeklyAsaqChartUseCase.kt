package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.enums.ProgramTag
import com.giftech.terbit.domain.model.FillOutAsaq
import com.giftech.terbit.domain.model.WeeklyAsaqChart
import com.giftech.terbit.domain.repository.IAsaqResponseRepository
import com.giftech.terbit.domain.repository.IProgramRepository
import com.giftech.terbit.domain.util.toSinglePrecision
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
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
            .flatMapLatest { weeklyAsaqProgramIdList ->
                asaqResponseRepository.getAll()
                    .mapLatest { asaqResponseList ->
                        asaqResponseList
                            .filter { it.programId in weeklyAsaqProgramIdList }
                            .sortedBy { it.questionId }
                    }
                    .mapLatest { asaqResponseList ->
                        val freqHoursList = asaqResponseList.map {
                            it.freq.toDouble() / 60
                        }
                        
                        val xLabels = asaqResponseList.map { "Q${it.questionId}" }
                        val maxFreq = freqHoursList.maxOrNull() ?: 0.0
                        val maxY = when {
                            maxFreq <= 3.0 -> 4
                            maxFreq <= 7.0 -> 8
                            maxFreq <= 11.0 -> 12
                            maxFreq <= 15.0 -> 16
                            maxFreq <= 19.0 -> 20
                            maxFreq <= 23.0 -> 24
                            else -> 32
                        }
                        val yLabelCount = 5
                        val sedentaryAverageHours = freqHoursList.average().toSinglePrecision()
                        
                        WeeklyAsaqChart(
                            entry = asaqResponseList,
                            xLabels = xLabels,
                            maxY = maxY,
                            yLabelCount = yLabelCount,
                            sedentaryAverageHours = sedentaryAverageHours,
                        )
                    }
            }
            .flowOn(Dispatchers.IO)
    }
    
}