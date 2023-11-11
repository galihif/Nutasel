package com.giftech.terbit.domain.model

import com.giftech.terbit.domain.enums.SedenterType

data class WeeklySummary(
    val week: Int,
    val hasPresented: Boolean,
    val sedentaryAverageHours: Float,
    val sedentaryLevel: SedenterType,
)