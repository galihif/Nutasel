package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.repository.IFfqQuestionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFfqResultUseCase @Inject constructor(
    private val ffqQuestionRepository: IFfqQuestionRepository,
) {
    
    operator fun invoke(
        programId: Int,
    ): Flow<Int> {
        return ffqQuestionRepository.getAll()
            .map { ffqQuestionList ->
                ffqQuestionList
                    .filter { it.programId == programId }
                    .sumOf { it.freq?.score ?: 0 }
            }
    }
    
}