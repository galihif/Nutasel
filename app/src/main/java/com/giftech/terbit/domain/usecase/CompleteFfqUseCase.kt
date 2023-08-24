package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.repository.IProgramRepository
import javax.inject.Inject

class CompleteFfqUseCase @Inject constructor(
    private val programRepository: IProgramRepository,
) {
    
    suspend operator fun invoke(
        programId: Int,
    ) {
        programRepository.insert(
            programId = programId,
            isComplete = true,
        )
    }
    
}