package com.giftech.terbit.domain.repository

import com.giftech.terbit.domain.enums.SedenterType
import com.giftech.terbit.domain.model.Asaq
import kotlinx.coroutines.flow.Flow

interface IAsaqRepository {

    fun getPreTestAsaq(): Flow<List<Asaq>>
    fun getPostTestAsaq(): Flow<List<Asaq>>

    fun getSedenterType(): Flow<SedenterType>


    suspend fun insertPreTestAsaq(
        asaq: List<Asaq>
    )

    suspend fun insertPostTestAsaq(
        asaq: List<Asaq>
    )
}