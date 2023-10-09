package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.model.AsaqResponse
import com.giftech.terbit.domain.repository.IAsaqResponseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetAsaqResponseUseCase @Inject constructor(
    private val asaqResponseRepository: IAsaqResponseRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(
        programId: Int,
    ): Flow<List<AsaqResponse>> {
        return asaqResponseRepository.getAll()
            .mapLatest { asaqResponseList ->
                asaqResponseList.filter { it.programId == programId }
            }
            .flowOn(Dispatchers.IO)
    }
    
}