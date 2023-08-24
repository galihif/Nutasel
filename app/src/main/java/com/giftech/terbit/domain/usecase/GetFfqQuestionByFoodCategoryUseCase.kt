package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.model.FfqQuestion
import com.giftech.terbit.domain.repository.IFfqQuestionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFfqQuestionByFoodCategoryUseCase @Inject constructor(
    private val ffqQuestionRepository: IFfqQuestionRepository,
) {
    
    operator fun invoke(
        programId: Int,
        foodCategoryId: Int,
    ): Flow<List<FfqQuestion>> {
        return ffqQuestionRepository.getByProgramId(programId)
            .map { ffqQuestionList ->
                ffqQuestionList.filter { it.foodCategoryId == foodCategoryId }
            }
    }
    
}