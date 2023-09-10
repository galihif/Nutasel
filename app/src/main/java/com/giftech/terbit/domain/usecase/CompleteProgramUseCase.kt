package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.repository.IProgramRepository
import com.giftech.terbit.domain.util.toMillis
import java.time.LocalDateTime
import javax.inject.Inject

class CompleteProgramUseCase @Inject constructor(
    private val programRepository: IProgramRepository,
) {
    
    suspend operator fun invoke(
        programId: Int,
    ) {
        val completionDate = LocalDateTime.now()
        programRepository.insert(
            programId = programId,
            isComplete = true,
            completionDateInMillis = completionDate.toMillis(),
        )
    }
    
}