package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.model.Program
import com.giftech.terbit.domain.repository.IProgramRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetProgramListByWeekUseCase @Inject constructor(
    private val programRepository: IProgramRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(
        week: Int,
    ): Flow<List<Program>> {
        return programRepository.getAll()
            .mapLatest { programList ->
                programList
                    .filter { it.week == week }
                    .sortedBy { it.dayOfWeek }
            }
            .flowOn(Dispatchers.IO)
    }
    
}