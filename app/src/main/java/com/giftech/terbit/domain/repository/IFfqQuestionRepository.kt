package com.giftech.terbit.domain.repository

import com.giftech.terbit.domain.enums.FfqFrequency
import com.giftech.terbit.domain.model.FfqQuestion
import kotlinx.coroutines.flow.Flow

interface IFfqQuestionRepository {
    
    fun getByProgramId(
        programId: Int,
    ): Flow<List<FfqQuestion>>
    
    // Insert a new food or response the question
    suspend fun insert(
        foodName: String,
        foodCategoryId: Int,
    )
    
    // Insert or update the response of question
    suspend fun insert(
        programId: Int,
        foodId: Int,
        freq: FfqFrequency,
    )
    
}