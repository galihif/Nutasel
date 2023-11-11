package com.giftech.terbit.data.mapper

import com.giftech.terbit.data.source.local.room.entity.WeeklySummaryEntity
import com.giftech.terbit.domain.enums.SedenterType
import com.giftech.terbit.domain.model.WeeklySummary
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeeklySummaryMapper @Inject constructor() {
    
    fun mapToDomain(input: List<WeeklySummaryEntity>): List<WeeklySummary> {
        return input.map { entity ->
            mapToDomain(entity)
        }
    }
    
    private fun mapToDomain(input: WeeklySummaryEntity): WeeklySummary {
        return WeeklySummary(
            week = input.week,
            hasPresented = input.hasPresented,
            sedentaryAverageHours = 0f,
            sedentaryLevel = SedenterType.RINGAN,
        )
    }
    
}