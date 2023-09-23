package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.enums.ProgramTag
import com.giftech.terbit.domain.repository.IProgramRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CheckPreTestDoneUseCase @Inject constructor(
    private val programRepository: IProgramRepository,
) {
    
    operator fun invoke(): Flow<Boolean> {
        return programRepository.getAll()
            .map { programList ->
                programList
                    .filter {
                        it.tag == ProgramTag.PRE_TEST
                    }
                    .all {
                        it.isCompleted
                    }
            }
    }
    
}