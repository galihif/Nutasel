package com.giftech.terbit.data.source.local

import com.giftech.terbit.data.source.local.room.dao.WeeklySummaryDao
import com.giftech.terbit.data.source.local.room.entity.WeeklySummaryEntity
import com.giftech.terbit.data.source.local.statics.WeeklySummaryData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeeklySummaryLocalDataSource @Inject constructor(
    private val weeklySummaryDao: WeeklySummaryDao,
    private val weeklySummaryData: WeeklySummaryData,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    fun getAll(): Flow<List<WeeklySummaryEntity>> {
        val weeklySummaryStaticListFlow = MutableStateFlow(
            weeklySummaryData.getAll()
        )
        
        return weeklySummaryStaticListFlow.flatMapLatest { weeklySummaryStaticList ->
            weeklySummaryDao.getAll()
                .map { weeklySummaryEntityList ->
                    weeklySummaryStaticList.map { weeklySummaryStatic ->
                        WeeklySummaryEntity(
                            week = weeklySummaryStatic.week,
                            hasPresented = weeklySummaryEntityList.firstOrNull {
                                it.week == weeklySummaryStatic.week
                            }?.hasPresented ?: false,
                        )
                    }
                }
        }
    }
    
    suspend fun insert(weeklySummaryEntity: WeeklySummaryEntity) {
        weeklySummaryDao.insert(weeklySummaryEntity)
    }
    
}