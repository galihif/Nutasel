package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.repository.IAsaqResponseRepository
import javax.inject.Inject

class SubmitAsaqResponseUseCase @Inject constructor(
    private val asaqResponseRepository: IAsaqResponseRepository,
) {
    
    suspend operator fun invoke(
        programId: Int,
        questionId: Int,
        freq: Int,
    ) {
        asaqResponseRepository.insert(
            programId = programId,
            questionId = questionId,
            freq = freq,
        )
    }
    
}