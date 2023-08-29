package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.model.Asaq
import com.giftech.terbit.domain.repository.IAsaqRepository
import javax.inject.Inject

class AsaqUseCase @Inject constructor(
    private val asaqRepository: IAsaqRepository
){
    fun getPreTestAsaq() = asaqRepository.getPreTestAsaq()
    fun getPostTestAsaq() = asaqRepository.getPostTestAsaq()
    suspend fun insertPreTestAsaq(asaq:List<Asaq>) = asaqRepository.insertPreTestAsaq(asaq)
    suspend fun insertPostTestAsaq(asaq:List<Asaq>) = asaqRepository.insertPostTestAsaq(asaq)

    fun getSedenterType() = asaqRepository.getSedenterType()
}