package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.enums.FfqFrequency
import com.giftech.terbit.domain.repository.IFfqQuestionRepository
import javax.inject.Inject

class SubmitFfqResponseUseCase @Inject constructor(
    private val ffqQuestionRepository: IFfqQuestionRepository,
) {
    
    suspend operator fun invoke(
        programId: Int,
        foodId: Int,
        freq: FfqFrequency,
    ) {
        ffqQuestionRepository.insert(
            programId = programId,
            foodId = foodId,
            freq = freq,
        )
    }
    
}