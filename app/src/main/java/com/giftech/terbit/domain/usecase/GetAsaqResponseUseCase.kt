package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.model.AsaqResponse
import com.giftech.terbit.domain.repository.IAsaqResponseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAsaqResponseUseCase @Inject constructor(
    private val asaqResponseRepository: IAsaqResponseRepository,
) {
    
    operator fun invoke(
        programId: Int,
    ): Flow<List<AsaqResponse>> {
        return asaqResponseRepository.getAll()
            .map { asaqResponseList ->
                asaqResponseList.filter { it.programId == programId }
            }
    }
    
}