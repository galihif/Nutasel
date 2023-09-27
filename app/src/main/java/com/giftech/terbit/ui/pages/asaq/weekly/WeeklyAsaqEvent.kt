package com.giftech.terbit.ui.pages.asaq.weekly

import com.giftech.terbit.ui.components.enums.AsaqQuestions

sealed class WeeklyAsaqEvent {
    
    data class Init(
        val programId: Int,
    ) : WeeklyAsaqEvent()
    
    data class EnteredHoursFreq(
        val value: String,
    ) : WeeklyAsaqEvent()
    
    data class EnteredMinutesFreq(
        val value: String,
    ) : WeeklyAsaqEvent()
    
    data class NextQuestion(
        val currentQuestion: AsaqQuestions,
    ) : WeeklyAsaqEvent()
    
    data class PreviousQuestion(
        val currentQuestion: AsaqQuestions,
    ) : WeeklyAsaqEvent()
    
    data class SubmitResponse(
        val programId: Int,
        val currentQuestion: AsaqQuestions,
        val hoursFreq: Int?,
        val minutesFreq: Int?,
    ) : WeeklyAsaqEvent()
    
    data class CompleteAsaq(
        val programId: Int,
    ) : WeeklyAsaqEvent()
    
}