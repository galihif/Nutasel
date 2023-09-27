package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.model.FfqCategoryChart
import com.giftech.terbit.domain.repository.IFfqQuestionRepository
import com.giftech.terbit.domain.util.Constants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetFfqCategoryChartUseCase @Inject constructor(
    private val ffqQuestionRepository: IFfqQuestionRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(
        ffqFoodCategoryId: Int,
    ): Flow<FfqCategoryChart> {
        return ffqQuestionRepository.getAll()
            .mapLatest { ffqResponseList ->
                ffqResponseList.filter { it.programId == Constants.ProgramId.LAST_FFQ }
            }
            .mapLatest { ffqResponseList ->
                ffqResponseList.filter { it.foodCategoryId == ffqFoodCategoryId }
            }
            .mapLatest { ffqResponseList ->
                val xLabels = ffqResponseList.map { it.foodName }
                val yLabels = listOf(
                    "0x",
                    "1x/H",
                    ">3x/H",
                    "1-2x/M",
                    "3-6x/M",
                    "2x/B",
                )
                
                FfqCategoryChart(
                    entry = ffqResponseList,
                    xLabels = xLabels,
                    yLabels = yLabels,
                )
            }
    }
    
}