package com.giftech.terbit.presentation.ui.pages.weeklysummary

sealed class WeeklySummaryEvent {
    
    data class Init(
        val week: Int,
    ) : WeeklySummaryEvent()
    
    data class UpdatePresentedStatus(
        val week: Int,
    ) : WeeklySummaryEvent()
    
}