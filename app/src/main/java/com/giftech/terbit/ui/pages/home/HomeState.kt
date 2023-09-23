package com.giftech.terbit.ui.pages.home

import com.giftech.terbit.domain.model.Program

data class HomeState(
    val isPreTestDone: Boolean,
    val userName: String,
    val bmiCategory: String,
    val monitoringLevel: String,
    val bmiValue: Double,
    val postTestOpeningDate: String,
    val isPostTestAvailable: Boolean,
    val isPostTestDone: Boolean,
    val isAllWeeklyProgramDone: Boolean,
    val nextDayProgramList: List<Program>,
    val isNextDayProgramAvailable: Boolean,
    val totalProgram: Int,
    val totalCompletedProgram: Int,
    val programProgressPercentage: Int,
    val totalCompletedDaysInWeek: Int,
    val currentWeek: Int,
    val totalCompletedWeek: Int,
    val totalWeek: Int,
    val isNotificationEmpty: Boolean,
)