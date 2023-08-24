package com.giftech.terbit.data.repository

import com.giftech.terbit.data.mapper.FfqFoodCategoryMapper
import com.giftech.terbit.data.source.local.FfqFoodCategoryLocalDataSource
import com.giftech.terbit.domain.model.FfqFoodCategory
import com.giftech.terbit.domain.repository.IFfqFoodCategoryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FfqFoodCategoryRepository @Inject constructor(
    private val ffqFoodCategoryLocalDataSource: FfqFoodCategoryLocalDataSource,
    private val ffqFoodCategoryMapper: FfqFoodCategoryMapper,
) : IFfqFoodCategoryRepository {
    
    override fun getAll(): List<FfqFoodCategory> {
        return ffqFoodCategoryLocalDataSource.getAll().let {
            ffqFoodCategoryMapper.mapToDomain(it)
        }
    }
    
}