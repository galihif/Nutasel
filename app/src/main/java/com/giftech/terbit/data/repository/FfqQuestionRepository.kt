package com.giftech.terbit.data.repository

import com.giftech.terbit.data.mapper.FfqQuestionMapper
import com.giftech.terbit.data.source.local.FfqFoodLocalDataSource
import com.giftech.terbit.data.source.local.FfqResponseLocalDataSource
import com.giftech.terbit.data.source.local.ProgramLocalDataSource
import com.giftech.terbit.data.source.local.room.entity.FfqFoodEntity
import com.giftech.terbit.data.source.local.room.entity.FfqResponseEntity
import com.giftech.terbit.domain.enums.FfqFrequency
import com.giftech.terbit.domain.model.FfqQuestion
import com.giftech.terbit.domain.repository.IFfqQuestionRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FfqQuestionRepository @Inject constructor(
    private val programLocalDataSource: ProgramLocalDataSource,
    private val ffqFoodLocalDataSource: FfqFoodLocalDataSource,
    private val ffqResponseLocalDataSource: FfqResponseLocalDataSource,
    private val ffqQuestionMapper: FfqQuestionMapper,
) : IFfqQuestionRepository {
    
    // The user responses of an FFQ can be empty
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAll(): Flow<List<FfqQuestion>> {
        return programLocalDataSource.getAll().flatMapLatest { programList ->
            ffqFoodLocalDataSource.getAll().flatMapLatest { ffqFoodEntityList ->
                ffqResponseLocalDataSource.getAll().mapLatest { ffqResponseEntityList ->
                    ffqQuestionMapper.mapToDomain(
                        input1 = programList,
                        input2 = ffqFoodEntityList,
                        input3 = ffqResponseEntityList,
                    )
                }
            }
        }
    }
    
    override suspend fun insert(foodName: String, foodCategoryId: Int) {
        val ffqFoodEntity = FfqFoodEntity(
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
    
}