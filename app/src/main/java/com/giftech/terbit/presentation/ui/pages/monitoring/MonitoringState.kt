package com.giftech.terbit.presentation.ui.pages.monitoring

import com.giftech.terbit.domain.model.Program
import java.time.LocalDate

data class MonitoringState(
    val completedDayList: List<LocalDate>,
    val weeklyProgramOpeningDate: String,
    val isWeeklyProgramAvailable: Boolean,
    val weeklyProgramList: Map<Int, List<Program>>,
)