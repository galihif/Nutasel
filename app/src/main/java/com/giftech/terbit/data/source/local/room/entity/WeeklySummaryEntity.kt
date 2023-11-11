package com.giftech.terbit.data.source.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeeklySummaryEntity(
    @PrimaryKey val week: Int,
    val hasPresented: Boolean,
)