package com.giftech.terbit.data.repository

import com.giftech.terbit.data.mapper.AsaqResponseMapper
import com.giftech.terbit.data.source.local.AsaqResponseLocalDataSource
import com.giftech.terbit.data.source.local.room.entity.AsaqResponseEntity
import com.giftech.terbit.domain.model.AsaqResponse
import com.giftech.terbit.domain.repository.IAsaqResponseRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AsaqResponseRepository @Inject constructor(
    private val asaqResponseLocalDataSource: AsaqResponseLocalDataSource,
    private val asaqResponseMapper: AsaqResponseMapper,
) : IAsaqResponseRepository {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getAll(): Flow<List<AsaqResponse>> {
        return asaqResponseLocalDataSource.getAll()
            .mapLatest {
                asaqResponseMapper.mapToDomain(it)
            }
    }
    
    override suspend fun insert(programId: Int, questionId: Int, freq: Int) {
        val asaqResponseEntity = AsaqResponseEntity(
            programId = programId,
            questionId = questionId,
            freq = freq,
        )
        asaqResponseLocalDataSource.insert(asaqResponseEntity)
    }
    
}