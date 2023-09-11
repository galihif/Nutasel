package com.giftech.terbit.domain.model

import java.time.LocalDate

data class MonitoringSummary(
    val completedDayList: List<LocalDate>,
    val weeklyProgramOpeningDate: String,
    val isWeeklyProgramAvailable: Boolean,
    val weeklyProgramList: Map<Int, List<Program>>,
)