package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.model.PostTestAsaqChart
import com.giftech.terbit.domain.repository.IAsaqRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetPostTestAsaqChartUseCase @Inject constructor(
    private val asaqRepository: IAsaqRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(): Flow<PostTestAsaqChart> {
        return asaqRepository.getPostTestAsaq()
            .mapLatest { asaqResponseList ->
                asaqResponseList.sortedBy { it.questionId }
            }
            .mapLatest { asaqResponseList ->
                val xLabels = asaqResponseList.map { "Q${it.questionId}" }
                val maxFreq = asaqResponseList.maxOfOrNull {
                    (it.durasiHariKerja.toDouble() * 5 + it.durasiHariLibur.toDouble() * 2) / 7 / 60
                } ?: 0.0
                val maxY = when {
                    maxFreq <= 4.0 -> 4
                    maxFreq <= 8.0 -> 8
                    maxFreq <= 12.0 -> 12
                    maxFreq <= 16.0 -> 16
                    maxFreq <= 20.0 -> 20
                    else -> 24
                }
                val yLabelCount = 5
    
                PostTestAsaqChart(
                    entry = asaqResponseList,
                    xLabels = xLabels,
                    maxY = maxY,
                    yLabelCount = yLabelCount,
                )
            }
            .flowOn(Dispatchers.IO)
    }
    
}