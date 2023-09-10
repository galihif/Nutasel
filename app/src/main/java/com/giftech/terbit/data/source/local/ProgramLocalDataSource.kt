package com.giftech.terbit.data.source.local

import com.giftech.terbit.data.source.local.room.dao.ProgramDao
import com.giftech.terbit.data.source.local.room.entity.ProgramEntity
import com.giftech.terbit.data.source.local.statics.ProgramData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProgramLocalDataSource @Inject constructor(
    private val programDao: ProgramDao,
    private val programData: ProgramData,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    fun getAll(): Flow<List<ProgramEntity>> {
        val programEntityListFlow = MutableStateFlow(programData.getAll())
        
        // The list from local DB (completion status) can be empty
        return programEntityListFlow.flatMapLatest { programEntityList ->
            programDao.getAll().map { listFromLocalDB ->
                
                programEntityList.forEachIndexed { index, programEntity ->
                    val programEntityFromLocalDB = listFromLocalDB
                        .firstOrNull { it.programId == programEntity.programId }
                    if (programEntityFromLocalDB != null) {
                        programEntityList[index].apply {
                            isComplete = programEntityFromLocalDB.isComplete
                            completionDateInMillis = programEntityFromLocalDB.completionDateInMillis
                        }
                    }
                }
                programEntityList
                
            }
        }
    }
    
    suspend fun insert(programEntity: ProgramEntity) {
        programDao.insert(programEntity)
    }
    
}