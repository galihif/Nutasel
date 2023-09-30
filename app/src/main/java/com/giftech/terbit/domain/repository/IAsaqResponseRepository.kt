package com.giftech.terbit.domain.repository

import com.giftech.terbit.domain.model.AsaqResponse
import kotlinx.coroutines.flow.Flow

interface IAsaqResponseRepository {
    
    suspend fun getAll(): Flow<List<AsaqResponse>>
    
    suspend fun insert(
        programId: Int,
        questionId: Int,
        freq: Int,
    )
    
}