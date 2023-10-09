package com.giftech.terbit.presentation.ui.pages.monitoringdetails

sealed class MonitoringDetailsEvent {
    
    data class Init(
        val week: Int,
    ) : MonitoringDetailsEvent()
    
}