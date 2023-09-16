package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.model.Program
import com.giftech.terbit.domain.repository.IProgramRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProgramListByWeekUseCase @Inject constructor(
    private val programRepository: IProgramRepository,
) {
    
    operator fun invoke(
        week: Int,
    ): Flow<List<Program>> {
        return programRepository.getAll()
            .map { programList ->
                programList
                    .filter { it.week == week }
                    .sortedBy { it.dayOfWeek }
            }
    }
    
}