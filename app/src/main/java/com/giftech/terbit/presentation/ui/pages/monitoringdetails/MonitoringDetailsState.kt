package com.giftech.terbit.presentation.ui.pages.monitoringdetails

import com.giftech.terbit.domain.model.Program

data class MonitoringDetailsState(
    val week: Int,
    val programList: List<Program>,
    val needLaunchFinishScreen: Boolean,
    val currentWeek: Int,
    val currentDayOfWeek: Int,
)