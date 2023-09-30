package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.enums.ProgramTag
import com.giftech.terbit.domain.repository.IProgramRepository
import com.giftech.terbit.domain.util.percentageOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetWeeklyProgramProgressUseCase @Inject constructor(
    private val programRepository: IProgramRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(): Flow<Int> {
        return programRepository.getAll()
            .mapLatest { programList ->
                val weeklyProgramList = programList.filter {
                    it.tag == ProgramTag.WEEKLY_PROGRAM
                }
                percentageOf(
                    value = weeklyProgramList.count { it.isCompleted },
                    total = weeklyProgramList.size,
                )
            }
            .flowOn(Dispatchers.IO)
    }
    
}