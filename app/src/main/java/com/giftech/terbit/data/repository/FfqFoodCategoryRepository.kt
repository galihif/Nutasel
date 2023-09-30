package com.giftech.terbit.data.repository

import com.giftech.terbit.data.mapper.FfqFoodCategoryMapper
import com.giftech.terbit.data.source.local.FfqFoodCategoryLocalDataSource
import com.giftech.terbit.domain.model.FfqFoodCategory
import com.giftech.terbit.domain.repository.IFfqFoodCategoryRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FfqFoodCategoryRepository @Inject constructor(
    private val ffqFoodCategoryLocalDataSource: FfqFoodCategoryLocalDataSource,
    private val ffqFoodCategoryMapper: FfqFoodCategoryMapper,
) : IFfqFoodCategoryRepository {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getAll(): Flow<List<FfqFoodCategory>> {
        return ffqFoodCategoryLocalDataSource.getAll()
            .mapLatest {
                ffqFoodCategoryMapper.mapToDomain(it)
            }
    }
    
}