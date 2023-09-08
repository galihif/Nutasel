package com.giftech.terbit.domain.repository

import com.giftech.terbit.domain.model.Program
import kotlinx.coroutines.flow.Flow

interface IProgramRepository {
    
    fun getAll(): Flow<List<Program>>
    
    // Insert to save completion status
    suspend fun insert(
        programId: Int,
        isComplete: Boolean,
        completionDateInMilis: Long,
    )
    
}