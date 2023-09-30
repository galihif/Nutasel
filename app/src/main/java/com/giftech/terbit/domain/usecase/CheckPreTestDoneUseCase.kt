package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.enums.ProgramTag
import com.giftech.terbit.domain.repository.IProgramRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class CheckPreTestDoneUseCase @Inject constructor(
    private val programRepository: IProgramRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(): Flow<Boolean> {
        return programRepository.getAll()
            .mapLatest { programList ->
                programList
                    .filter {
                        it.tag == ProgramTag.PRE_TEST
                    }
                    .all {
                        it.isCompleted
                    }
            }
            .flowOn(Dispatchers.IO)
    }
    
}