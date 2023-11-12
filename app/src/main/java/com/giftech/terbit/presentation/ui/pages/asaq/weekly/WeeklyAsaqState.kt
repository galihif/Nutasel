package com.giftech.terbit.presentation.ui.pages.asaq.weekly

import com.giftech.terbit.domain.model.AsaqResponse
import com.giftech.terbit.presentation.ui.components.enums.AsaqQuestions

data class WeeklyAsaqState(
    val programId: Int,
    val currentQuestion: AsaqQuestions,
    val responseList: List<AsaqResponse>,
    val hoursFreq: Int?,
    val minutesFreq: Int?,
    val isFirstQuestion: Boolean,
    val isLastQuestion: Boolean,
    val sedentaryAverageHours: Float,
)