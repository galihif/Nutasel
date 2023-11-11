package com.giftech.terbit.domain.repository

import com.giftech.terbit.domain.model.WeeklySummary
import kotlinx.coroutines.flow.Flow

interface IWeeklySummaryRepository {
    
    suspend fun getAll(): Flow<List<WeeklySummary>>
    
    suspend fun updateHasPresented(week: Int, hasPresented: Boolean)
    
}