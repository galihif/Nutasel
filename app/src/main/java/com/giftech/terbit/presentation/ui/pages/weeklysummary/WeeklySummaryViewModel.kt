package com.giftech.terbit.presentation.ui.pages.weeklysummary

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftech.terbit.domain.usecase.GetWeeklySummaryUseCase
import com.giftech.terbit.domain.usecase.MarkWeeklySummaryAsPresentedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeeklySummaryViewModel @Inject constructor(
    private val getWeeklySummaryUseCase: GetWeeklySummaryUseCase,
    private val markWeeklySummaryAsPresentedUseCase: MarkWeeklySummaryAsPresentedUseCase,
) : ViewModel() {
    
    private val _state = mutableStateOf(WeeklySummaryState())
    val state: State<WeeklySummaryState> = _state
    
    fun onEvent(event: WeeklySummaryEvent) {
        when (event) {
            is WeeklySummaryEvent.Init -> {
                viewModelScope.launch {
                    getWeeklySummaryUseCase().collect { weeklySummary ->
                        _state.value = _state.value.copy(
                            week = weeklySummary.week,
                            sedentaryAverageHours = weeklySummary.sedentaryAverageHours,
                            sedentaryLevel = weeklySummary.sedentaryLevel,
                        )
                    }
                }
            }
            
            is WeeklySummaryEvent.UpdatePresentedStatus -> {
                viewModelScope.launch {
                    markWeeklySummaryAsPresentedUseCase(
                        week = event.week,
                    )
                }
            }
        }
    }
    
}