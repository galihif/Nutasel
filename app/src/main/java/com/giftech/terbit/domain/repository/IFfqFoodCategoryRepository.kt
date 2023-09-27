package com.giftech.terbit.domain.repository

import com.giftech.terbit.domain.model.FfqFoodCategory
import kotlinx.coroutines.flow.Flow

interface IFfqFoodCategoryRepository {
    
    fun getAll(): Flow<List<FfqFoodCategory>>
    
}