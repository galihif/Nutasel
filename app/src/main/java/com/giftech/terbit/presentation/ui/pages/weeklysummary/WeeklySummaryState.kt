package com.giftech.terbit.presentation.ui.pages.weeklysummary

import com.giftech.terbit.domain.enums.SedenterType

data class WeeklySummaryState(
    val week: Int = 0,
    val sedentaryAverageHours: Float = 0f,
    val sedentaryLevel: SedenterType = SedenterType.RINGAN,
)