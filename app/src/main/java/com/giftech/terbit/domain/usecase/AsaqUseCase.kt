package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.model.Asaq
import com.giftech.terbit.domain.repository.IAsaqRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AsaqUseCase @Inject constructor(
    private val asaqRepository: IAsaqRepository,
) {
    
    suspend fun getPreTestAsaq() = asaqRepository.getPreTestAsaq()
        .flowOn(Dispatchers.IO)
    
    suspend fun getPostTestAsaq() = asaqRepository.getPostTestAsaq()
        .flowOn(Dispatchers.IO)
    
    suspend fun insertPreTestAsaq(asaq: List<Asaq>) = asaqRepository.insertPreTestAsaq(asaq)
    
    suspend fun insertPostTestAsaq(asaq: List<Asaq>) = asaqRepository.insertPostTestAsaq(asaq)
    
    suspend fun getSedenterType() = asaqRepository.getSedenterType()
        .flowOn(Dispatchers.IO)
    
}