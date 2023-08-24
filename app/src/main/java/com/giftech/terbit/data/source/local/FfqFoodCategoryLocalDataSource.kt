package com.giftech.terbit.data.source.local

import com.giftech.terbit.data.source.local.statics.FfqFoodCategoryData
import com.giftech.terbit.data.source.local.statics.model.FfqFoodCategoryEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FfqFoodCategoryLocalDataSource @Inject constructor(
    private val ffqFoodCategoryData: FfqFoodCategoryData,
) {
    
    fun getAll(): List<FfqFoodCategoryEntity> {
        return ffqFoodCategoryData.getAll()
    }
    
}