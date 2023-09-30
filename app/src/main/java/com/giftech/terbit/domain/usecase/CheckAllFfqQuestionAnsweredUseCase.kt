package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.repository.IFfqQuestionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class CheckAllFfqQuestionAnsweredUseCase @Inject constructor(
    private val ffqQuestionRepository: IFfqQuestionRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(
        programId: Int,
    ): Flow<Boolean> {
        return ffqQuestionRepository.getAll()
            .mapLatest { questionList ->
                questionList
                    .filter { it.programId == programId }
                    .all { it.freq != null }
            }
            .flowOn(Dispatchers.IO)
    }
    
}