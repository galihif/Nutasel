package com.giftech.terbit.ui.pages.monitoringdetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftech.terbit.domain.usecase.GetCurrentDayUseCase
import com.giftech.terbit.domain.usecase.GetProgramListByWeekUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MonitoringDetailsViewModel @Inject constructor(
    private val getProgramListByWeekUseCase: GetProgramListByWeekUseCase,
    private val getCurrentDayUseCase: GetCurrentDayUseCase,
) : ViewModel() {
    
    private val _state = mutableStateOf(
        MonitoringDetailsState(
            week = -1,
            programList = emptyList(),
            needLaunchFinishScreen = false,
            currentWeek = -1,
            currentDayOfWeek = -1,
        )
    )
    val state: State<MonitoringDetailsState> = _state
    
    fun onEvent(event: MonitoringDetailsEvent) {
        when (event) {
            is MonitoringDetailsEvent.Init -> {
                viewModelScope.launch {
                    val weekCompletionHistory = mutableListOf<Boolean>()
                    getProgramListByWeekUseCase(
                        week = event.week,
                    ).collect { programList ->
                        weekCompletionHistory.add(programList.all { it.isCompleted })
                        _state.value = _state.value.copy(
                            week = event.week,
                            programList = programList,
                            needLaunchFinishScreen = weekCompletionHistory.size > 1 &&
                                    !weekCompletionHistory.first() && weekCompletionHistory.last(),
                        )
                    }
                }
                viewModelScope.launch {
                    getCurrentDayUseCase()
                        .collect { (week, dayOfWeek) ->
                            _state.value = _state.value.copy(
                                currentWeek = week,
                                currentDayOfWeek = dayOfWeek,
                            )
                        }
                }
            }
        }
    }
    
}