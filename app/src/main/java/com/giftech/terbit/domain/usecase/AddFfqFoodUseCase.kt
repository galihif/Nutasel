package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.repository.IFfqQuestionRepository
import javax.inject.Inject

class AddFfqFoodUseCase @Inject constructor(
    private val ffqQuestionRepository: IFfqQuestionRepository,
){

        suspend operator fun invoke(
        foodName: String,
        foodCategoryId: Int,
    ) {
        ffqQuestionRepository.insert(
            foodName = foodName,
            foodCategoryId = foodCategoryId,
        )
    }

}