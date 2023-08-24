package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.repository.IFfqQuestionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CheckAllFfqQuestionAnsweredUseCase @Inject constructor(
    private val ffqQuestionRepository: IFfqQuestionRepository,
) {
    
    operator fun invoke(
        programId: Int,
    ): Flow<Boolean> {
        return ffqQuestionRepository.getByProgramId(programId).map { questionList ->
            questionList.all { it.freq != null }
        }
    }
    
}