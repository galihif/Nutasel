package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.model.PreTestAsaqChart
import com.giftech.terbit.domain.repository.IAsaqRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetPreTestAsaqChartUseCase @Inject constructor(
    private val asaqRepository: IAsaqRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(): Flow<PreTestAsaqChart> {
        return asaqRepository.getPreTestAsaq()
            .mapLatest { asaqResponseList ->
                asaqResponseList.sortedBy { it.questionId }
            }
            .mapLatest { asaqResponseList ->
                val xLabels = asaqResponseList.map { "Q${it.questionId}" }
                val maxFreq = asaqResponseList.maxOf {
                    (it.durasiHariKerja.toDouble() * 5 + it.durasiHariLibur.toDouble() * 2) / 7 / 60
                }
                val maxY = when {
                    maxFreq <= 4.0 -> 4
                    maxFreq <= 8.0 -> 8
                    maxFreq <= 12.0 -> 12
                    maxFreq <= 16.0 -> 16
                    maxFreq <= 20.0 -> 20
                    else -> 24
                }
                val yLabelCount = 5
    
                PreTestAsaqChart(
                    entry = asaqResponseList,
                    xLabels = xLabels,
                    maxY = maxY,
                    yLabelCount = yLabelCount,
                )
            }
    }
    
}