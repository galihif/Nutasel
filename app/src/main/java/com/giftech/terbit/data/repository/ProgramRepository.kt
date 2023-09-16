package com.giftech.terbit.data.repository

import com.giftech.terbit.data.mapper.ProgramMapper
import com.giftech.terbit.data.source.local.ProgramLocalDataSource
import com.giftech.terbit.data.source.local.room.dao.ProgramDao
import com.giftech.terbit.data.source.local.room.entity.ProgramEntity
import com.giftech.terbit.domain.model.Program
import com.giftech.terbit.domain.repository.IProgramRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProgramRepository @Inject constructor(
    private val programLocalDataSource: ProgramLocalDataSource,
    private val programDao: ProgramDao,
    private val programMapper: ProgramMapper,
) : IProgramRepository {
    
    override fun getAll(): Flow<List<Program>> {
        return programLocalDataSource.getAll()
            .map {
                programMapper.mapToDomain(it)
            }
    }
    
    override suspend fun insert(
        programId: Int,
        isCompleted: Boolean,
        completionDateInMillis: Long,
    ) {
        val programEntity = ProgramEntity(
            programId = programId,
            isCompleted = isCompleted,
            completionDateInMillis = completionDateInMillis,
            week = null, // Ignored
            dayOfWeek = null, // Ignored
            type = "", // Ignored
            tag = "", // Ignored
        )
        programDao.insert(programEntity)
    }
    
}