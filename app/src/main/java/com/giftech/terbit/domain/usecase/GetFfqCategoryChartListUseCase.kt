package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.model.FfqCategoryChart
import com.giftech.terbit.domain.repository.IFfqQuestionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetFfqCategoryChartListUseCase @Inject constructor(
    private val ffqQuestionRepository: IFfqQuestionRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(
        programId: Int,
    ): Flow<List<FfqCategoryChart>> {
        return ffqQuestionRepository.getAll()
            .mapLatest { ffqResponseList ->
                ffqResponseList
                    .filter { it.programId == programId && it.freq != null }
                    .groupBy { it.foodCategoryId }
            }
            .mapLatest { ffqResponseList ->
                val result = mutableListOf<FfqCategoryChart>()
                
                val yLabels = listOf(
                    "0x",
                    "1x/H",
                    ">3x/H",
                    "1-2x/M",
                    "3-6x/M",
                    "2x/B",
                )
                ffqResponseList.forEach {
                    val xLabels = it.value.map { ffqResponse ->
                        ffqResponse.foodName
                    }
                    result.add(
                        FfqCategoryChart(
                            entry = it.value,
                            xLabels = xLabels,
                            yLabels = yLabels,
                        )
                    )
                }
                
                result
            }
            .flowOn(Dispatchers.IO)
    }
    
}