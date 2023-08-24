package com.giftech.terbit.data.repository

import com.giftech.terbit.data.mapper.FfqQuestionMapper
import com.giftech.terbit.data.source.local.FfqFoodLocalDataSource
import com.giftech.terbit.data.source.local.FfqResponseLocalDataSource
import com.giftech.terbit.data.source.local.room.entity.FfqFoodEntity
import com.giftech.terbit.data.source.local.room.entity.FfqResponseEntity
import com.giftech.terbit.domain.enums.FfqFrequency
import com.giftech.terbit.domain.model.FfqQuestion
import com.giftech.terbit.domain.repository.IFfqQuestionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FfqQuestionRepository @Inject constructor(
    private val ffqFoodLocalDataSource: FfqFoodLocalDataSource,
    private val ffqResponseLocalDataSource: FfqResponseLocalDataSource,
    private val ffqQuestionMapper: FfqQuestionMapper,
) : IFfqQuestionRepository {
    
    // The user responses of an FFQ can be empty
    override fun getByProgramId(programId: Int): Flow<List<FfqQuestion>> {
        return ffqFoodLocalDataSource.getAll().flatMapLatest { ffqFood ->
            ffqResponseLocalDataSource.getAll().map { ffqResponse ->
                ffqQuestionMapper.mapToDomain(programId, ffqFood, ffqResponse)
            }
        }
    }
    
    override suspend fun insert(foodName: String, foodCategoryId: Int) {
        val ffqFoodEntity = FfqFoodEntity(
            foodId = 0, // Auto-generated
            foodCategoryId = foodCategoryId,
            name = foodName,
        )
        ffqFoodLocalDataSource.insert(ffqFoodEntity)
    }
    
    override suspend fun insert(programId: Int, foodId: Int, freq: FfqFrequency) {
        val ffqResponseEntity = FfqResponseEntity(
            programId = programId,
            foodId = foodId,
            freq = freq.freqId,
        )
        ffqResponseLocalDataSource.insert(ffqResponseEntity)
    }
    
    override suspend fun update(programId: Int, foodId: Int, freq: FfqFrequency) {
        val ffqResponseEntity = FfqResponseEntity(
            programId = programId,
            foodId = foodId,
            freq = freq.freqId,
        )
        ffqResponseLocalDataSource.update(ffqResponseEntity)
    }
    
    
}