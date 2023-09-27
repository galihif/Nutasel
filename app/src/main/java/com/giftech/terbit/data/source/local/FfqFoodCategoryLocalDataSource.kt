package com.giftech.terbit.data.source.local

import com.giftech.terbit.data.source.local.statics.FfqFoodCategoryData
import com.giftech.terbit.data.source.local.statics.model.FfqFoodCategoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FfqFoodCategoryLocalDataSource @Inject constructor(
    private val ffqFoodCategoryData: FfqFoodCategoryData,
) {
    
    fun getAll(): Flow<List<FfqFoodCategoryEntity>> {
        return flowOf(ffqFoodCategoryData.getAll())
    }
    
}