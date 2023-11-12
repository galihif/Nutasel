package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.model.PreTestAsaqChart
import com.giftech.terbit.domain.repository.IAsaqRepository
import com.giftech.terbit.domain.util.toSinglePrecision
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetPreTestAsaqChartUseCase @Inject constructor(
    private val asaqRepository: IAsaqRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(): Flow<PreTestAsaqChart> {
        return asaqRepository.getPreTestAsaq()
            .mapLatest { asaqResponseList ->
                asaqResponseList.sortedBy { it.questionId }
            }
            .mapLatest { asaqResponseList ->
                val freqHoursList = asaqResponseList.map {
                    (it.durasiHariKerja.toDouble() * 5 + it.durasiHariLibur.toDouble() * 2) / 7 / 60
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
    
                PreTestAsaqChart(
                    entry = asaqResponseList,
                    xLabels = xLabels,
                    maxY = maxY,
                    yLabelCount = yLabelCount,
                    sedentaryAverageHours = sedentaryAverageHours,
                )
            }
            .flowOn(Dispatchers.IO)
    }
    
}