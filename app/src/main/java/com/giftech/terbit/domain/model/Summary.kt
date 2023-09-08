package com.giftech.terbit.domain.model

data class Summary(
    val userName: String,
    val bmiCategory: String,
    val monitoringLevel: String,
    val bmiValue: Double,
    val postTestOpeningDate: String,
    val isPostTestAvailable: Boolean,
    val isPostTestDone: Boolean,
    val isAllWeeklyProgramDone: Boolean,
    val nextDayProgramList: List<Program>,
    val totalProgram: Int,
    val totalCompletedProgram: Int,
    val programProgressPercentage: Int,
    val totalCompletedDaysInWeek: Int,
    val currentWeek: Int,
    val totalCompletedWeek: Int,
    val totalWeek: Int,
)