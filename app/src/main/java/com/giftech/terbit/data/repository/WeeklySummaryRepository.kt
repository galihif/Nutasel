package com.giftech.terbit.data.repository

import com.giftech.terbit.data.mapper.WeeklySummaryMapper
import com.giftech.terbit.data.source.local.WeeklySummaryLocalDataSource
import com.giftech.terbit.data.source.local.room.entity.WeeklySummaryEntity
import com.giftech.terbit.domain.model.WeeklySummary
import com.giftech.terbit.domain.repository.IWeeklySummaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeeklySummaryRepository @Inject constructor(
    private val weeklySummaryLocalDataSource: WeeklySummaryLocalDataSource,
    private val weeklySummaryMapper: WeeklySummaryMapper,
) : IWeeklySummaryRepository {
    
    override suspend fun getAll(): Flow<List<WeeklySummary>> {
        return weeklySummaryLocalDataSource.getAll().map {
            weeklySummaryMapper.mapToDomain(it)
        }
    }
    
    override suspend fun updateHasPresented(
        week: Int,
        hasPresented: Boolean,
    ) {
        val weeklySummaryEntity = WeeklySummaryEntity(
            week = week,
            hasPresented = hasPresented,
        )
        weeklySummaryLocalDataSource.insert(weeklySummaryEntity)
    }
    
}