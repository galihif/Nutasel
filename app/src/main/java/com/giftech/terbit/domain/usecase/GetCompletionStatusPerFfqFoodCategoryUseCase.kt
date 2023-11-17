package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.repository.IFfqFoodCategoryRepository
import com.giftech.terbit.domain.repository.IFfqQuestionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetCompletionStatusPerFfqFoodCategoryUseCase @Inject constructor(
    private val ffqFoodCategoryRepository: IFfqFoodCategoryRepository,
    private val ffqQuestionRepository: IFfqQuestionRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(
        programId: Int,
    ): Flow<Map<Int, Boolean>> {
        return ffqFoodCategoryRepository.getAll()
            .flatMapConcat { foodCategoryList ->
                ffqQuestionRepository.getAll()
                    .mapLatest { questionList ->
                        questionList.filter { it.programId == programId }
                    }
                    .mapLatest { questionList ->
                        foodCategoryList.associate { foodCategory ->
                            foodCategory.foodCategoryId to questionList
                                .filter { question ->
                                    question.foodCategoryId == foodCategory.foodCategoryId
                                }
                                .all { question ->
                                    question.freq != null
                                }
                        }
                    }
            }
            .flowOn(Dispatchers.IO)
    }
    
}