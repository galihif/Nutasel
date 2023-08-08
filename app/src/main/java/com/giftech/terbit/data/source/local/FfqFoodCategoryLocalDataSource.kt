package com.giftech.terbit.data.source.local

import com.giftech.terbit.data.source.local.statics.FfqFoodCategoryData
import com.giftech.terbit.data.source.local.statics.model.FfqFoodCategoryEntity

class FfqFoodCategoryLocalDataSource {
    
    fun getAll(): List<FfqFoodCategoryEntity> {
        return FfqFoodCategoryData.get()
    }
    
}