package com.giftech.terbit.domain.repository

import com.giftech.terbit.domain.model.FfqFoodCategory

interface IFfqFoodCategoryRepository {
    
    fun getAll(): List<FfqFoodCategory>
    
}