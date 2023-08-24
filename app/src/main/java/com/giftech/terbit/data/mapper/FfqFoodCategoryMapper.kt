package com.giftech.terbit.data.mapper

import com.giftech.terbit.data.source.local.statics.model.FfqFoodCategoryEntity
import com.giftech.terbit.domain.model.FfqFoodCategory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FfqFoodCategoryMapper @Inject constructor() {
    
    fun mapToDomain(input: List<FfqFoodCategoryEntity>): List<FfqFoodCategory> {
        return input.map { entity ->
            mapToDomain(entity)
        }
    }
    
    private fun mapToDomain(input: FfqFoodCategoryEntity): FfqFoodCategory {
        return FfqFoodCategory(
            foodCategoryId = input.foodCategoryId,
            name = input.name,
            imageRes = input.imageRes,
        )
    }
    
}