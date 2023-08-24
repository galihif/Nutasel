package com.giftech.terbit.data.source.local

import com.giftech.terbit.data.source.local.room.dao.ProgramDao
import com.giftech.terbit.data.source.local.room.entity.ProgramEntity
import com.giftech.terbit.data.source.local.statics.ProgramData
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
    
    fun getAll(): Flow<List<ProgramEntity>> {
        val programEntityList = programData.getAll()
        
        // The list from local DB (completion status) can be empty
        return MutableStateFlow(programEntityList).flatMapLatest { programEntityList ->
            programDao.getAll().map { listFromLocalDB ->
                val mappedListFromLocalDB = listFromLocalDB.map { it.programId }
                programEntityList.forEachIndexed { index, programEntity ->
                    if (mappedListFromLocalDB.contains(programEntity.programId)) {
                        programEntityList[index].isComplete = true
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