package com.giftech.terbit.ui.pages.monitoring

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftech.terbit.domain.usecase.GetMonitoringSummaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MonitoringViewModel @Inject constructor(
    private val getMonitoringSummaryUseCase: GetMonitoringSummaryUseCase,
) : ViewModel() {
    
    private val _state = mutableStateOf(
        MonitoringState(
            completedDayList = emptyList(),
            weeklyProgramOpeningDate = "",
            isWeeklyProgramAvailable = false,
            weeklyProgramList = emptyMap(),
        )
    )
    val state: State<MonitoringState> = _state
    
    init {
        getSummary()
    }
    
    private fun getSummary() {
        viewModelScope.launch {
            getMonitoringSummaryUseCase().collect {
                _state.value = MonitoringState(
                    completedDayList = it.completedDayList,
                    weeklyProgramOpeningDate = it.weeklyProgramOpeningDate,
                    isWeeklyProgramAvailable = it.isWeeklyProgramAvailable,
                    weeklyProgramList = it.weeklyProgramList,
                )
            }
        }
    }
    
}