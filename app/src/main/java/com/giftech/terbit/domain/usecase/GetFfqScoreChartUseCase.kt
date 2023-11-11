package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.model.FfqScoreChart
import com.giftech.terbit.domain.repository.IFfqFoodCategoryRepository
import com.giftech.terbit.domain.repository.IFfqQuestionRepository
import com.giftech.terbit.domain.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetFfqScoreChartUseCase @Inject constructor(
    private val ffqQuestionRepository: IFfqQuestionRepository,
    private val ffqFoodCategoryRepository: IFfqFoodCategoryRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(): Flow<FfqScoreChart> {
        return ffqQuestionRepository.getAll()
            .flatMapLatest { ffqResponseList ->
                ffqFoodCategoryRepository.getAll()
                    .mapLatest { foodCategoryList ->
                        val entry1 = ffqResponseList
                            .filter { it.programId == Constants.ProgramId.FIRST_FFQ }
                        val entry2 = ffqResponseList
                            .filter { it.programId == Constants.ProgramId.LAST_FFQ }
                        val xLabels = foodCategoryList.map { it.abbreviation }
                        val maxY = if (
                            entry1.maxOf { it.freq?.score ?: 0 } < 250 ||
                            entry2.maxOf { it.freq?.score ?: 0 } < 250
                        ) 300 else 350
                        val yLabelCount = 7
                        
                        FfqScoreChart(
                            entry1 = entry1,
                            entry2 = entry2,
                            xLabels = xLabels,
                            maxY = maxY,
                            yLabelCount = yLabelCount,
                        )
                    }
            }
            .flowOn(Dispatchers.IO)
    }
    
}