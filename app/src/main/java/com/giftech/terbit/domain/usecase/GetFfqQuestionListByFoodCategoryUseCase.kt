package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.model.FfqQuestion
import com.giftech.terbit.domain.repository.IFfqQuestionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetFfqQuestionListByFoodCategoryUseCase @Inject constructor(
    private val ffqQuestionRepository: IFfqQuestionRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(
        programId: Int,
        foodCategoryId: Int,
    ): Flow<List<FfqQuestion>> {
        return ffqQuestionRepository.getAll()
            .mapLatest { ffqQuestionList ->
                ffqQuestionList
                    .filter { it.programId == programId && it.foodCategoryId == foodCategoryId }
            }
            .flowOn(Dispatchers.IO)
    }
    
}