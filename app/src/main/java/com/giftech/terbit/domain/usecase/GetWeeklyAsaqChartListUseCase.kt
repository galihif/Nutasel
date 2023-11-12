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

class GetWeeklyAsaqChartListUseCase @Inject constructor(
    private val asaqResponseRepository: IAsaqResponseRepository,
    private val programRepository: IProgramRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(): Flow<List<WeeklyAsaqChart>> {
        return programRepository.getAll()
            .mapLatest { programList ->
                programList
                    .filterIsInstance(FillOutAsaq::class.java)
                    .filter { it.tag == ProgramTag.WEEKLY_PROGRAM }
            }
            .flatMapLatest { programList ->
                asaqResponseRepository.getAll()
                    .mapLatest { asaqResponseList ->
                        asaqResponseList
                            .sortedBy { it.questionId }
                    }
                    .mapLatest { asaqResponseList ->
                        val result = mutableListOf<WeeklyAsaqChart>()
                        
                        val yLabelCount = 5
                        for (week in 1..4) {
                            for (dayOfWeek in 1..7) {
                                val weeklyAsaqProgramIdList = programList
                                    .filter { it.week == week && it.dayOfWeek == dayOfWeek }
                                    .map { it.programId }
                                val asaqResponseListByProgramIds = asaqResponseList
                                    .filter { it.programId in weeklyAsaqProgramIdList }
                                val freqHoursList = asaqResponseListByProgramIds.map {
                                    it.freq.toDouble() / 60
                                }
                                
                                val xLabels =
                                    asaqResponseListByProgramIds.map { "Q${it.questionId}" }
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
                                val sedentaryAverageHours = freqHoursList.average().toSinglePrecision()
                                
                                result.add(
                                    WeeklyAsaqChart(
                                        entry = asaqResponseListByProgramIds,
                                        xLabels = xLabels,
                                        maxY = maxY,
                                        yLabelCount = yLabelCount,
                                        sedentaryAverageHours = sedentaryAverageHours,
                                    )
                                )
                            }
                        }
                        
                        result
                    }
            }
            .flowOn(Dispatchers.IO)
    }
    
}