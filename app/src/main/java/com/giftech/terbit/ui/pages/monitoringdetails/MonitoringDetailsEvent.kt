package com.giftech.terbit.ui.pages.monitoringdetails

sealed class MonitoringDetailsEvent {
    
    data class Init(
        val week: Int,
    ) : MonitoringDetailsEvent()
    
}