package com.giftech.terbit.domain.repository

import com.giftech.terbit.domain.enums.SedenterType
import com.giftech.terbit.domain.model.Asaq
import kotlinx.coroutines.flow.Flow

interface IAsaqRepository {
    
    suspend fun getPreTestAsaq(): Flow<List<Asaq>>
    
    suspend fun getPostTestAsaq(): Flow<List<Asaq>>
    
    suspend fun getSedenterType(): Flow<SedenterType>

    suspend fun getAsaqAverage(): Flow<Double>
    
    
    suspend fun insertPreTestAsaq(
        asaq: List<Asaq>,
    )
    
    suspend fun insertPostTestAsaq(
        asaq: List<Asaq>,
    )
    
}