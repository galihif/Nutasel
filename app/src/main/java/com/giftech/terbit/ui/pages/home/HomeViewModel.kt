package com.giftech.terbit.ui.pages.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftech.terbit.domain.usecase.CheckPreTestDoneUseCase
import com.giftech.terbit.domain.usecase.GetHomeSummaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeSummaryUseCase: GetHomeSummaryUseCase,
    private val checkPreTestDoneUseCase: CheckPreTestDoneUseCase,
) : ViewModel() {
    
    private val _state = mutableStateOf(
        HomeState(
            isPreTestDone = true,
            userName = "",
            bmiCategory = "",
            monitoringLevel = "",
            bmiValue = 0.0,
            postTestOpeningDate = "",
            isPostTestAvailable = false,
            isPostTestDone = false,
            isAllWeeklyProgramDone = false,
            nextDayProgramList = emptyList(),
            isNextDayProgramAvailable = false,
            totalProgram = 0,
            totalCompletedProgram = 0,
            programProgressPercentage = 0,
            totalCompletedDaysInWeek = 0,
            currentWeek = 0,
            totalCompletedWeek = 0,
            totalWeek = 0,
            isNotificationEmpty = true,
        )
    )
    val state: State<HomeState> = _state
    
    init {
        getSummary()
        checkPreTestDone()
    }
    
    private fun getSummary() {
        viewModelScope.launch {
            getHomeSummaryUseCase().collect { summary ->
                _state.value = _state.value.copy(
                    userName = summary.userName,
                    bmiCategory = summary.bmiCategory,
                    monitoringLevel = summary.monitoringLevel,
                    bmiValue = summary.bmiValue,
                    postTestOpeningDate = summary.postTestOpeningDate,
                    isPostTestAvailable = summary.isPostTestAvailable,
                    isPostTestDone = summary.isPostTestDone,
                    isAllWeeklyProgramDone = summary.isAllWeeklyProgramDone,
                    nextDayProgramList = summary.nextDayProgramList,
                    isNextDayProgramAvailable = summary.isNextDayProgramAvailable,
                    totalProgram = summary.totalProgram,
                    totalCompletedProgram = summary.totalCompletedProgram,
                    programProgressPercentage = summary.programProgressPercentage,
                    totalCompletedDaysInWeek = summary.totalCompletedDaysInWeek,
                    currentWeek = summary.currentWeek,
                    totalCompletedWeek = summary.totalCompletedWeek,
                    totalWeek = summary.totalWeek,
                    isNotificationEmpty = summary.isNotificationEmpty,
                )
            }
        }
    }
    
    private fun checkPreTestDone() {
        viewModelScope.launch {
            checkPreTestDoneUseCase().collect { isPreTestDone ->
                _state.value = _state.value.copy(
                    isPreTestDone = isPreTestDone,
                )
            }
        }
    }
    
}