package com.giftech.terbit.ui.pages.asaq.weekly

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftech.terbit.domain.usecase.CompleteProgramUseCase
import com.giftech.terbit.domain.usecase.GetAsaqResponseUseCase
import com.giftech.terbit.domain.usecase.SubmitAsaqResponseUseCase
import com.giftech.terbit.ui.components.enums.AsaqQuestions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeeklyAsaqViewModel @Inject constructor(
    private val getAsaqResponseUseCase: GetAsaqResponseUseCase,
    private val submitAsaqResponseUseCase: SubmitAsaqResponseUseCase,
    private val completeProgramUseCase: CompleteProgramUseCase,
) : ViewModel() {
    
    private val _state = mutableStateOf(
        WeeklyAsaqState(
            programId = -1,
            currentQuestion = AsaqQuestions.values().first(),
            responseList = emptyList(),
            hoursFreq = null,
            minutesFreq = null,
            isFirstQuestion = true,
            isLastQuestion = false,
        )
    )
    val state: State<WeeklyAsaqState> = _state
    
    fun onEvent(event: WeeklyAsaqEvent) {
        when (event) {
            is WeeklyAsaqEvent.Init -> {
                _state.value = _state.value.copy(
                    programId = event.programId,
                )
                
                viewModelScope.launch {
                    getAsaqResponseUseCase(
                        programId = event.programId
                    ).collect { asaqResponseList ->
                        val currentQuestion = _state.value.currentQuestion
                        val freq = asaqResponseList.firstOrNull { response ->
                            response.questionId == currentQuestion.questionId
                        }?.freq
                        _state.value = _state.value.copy(
                            responseList = asaqResponseList,
                            hoursFreq = freq?.div(60),
                            minutesFreq = freq?.rem(60),
                        )
                    }
                }
            }
            
            // Max 24:00 and only accept numeric input
            is WeeklyAsaqEvent.EnteredHoursFreq -> {
                val value = event.value
                    .filter { it.isDigit() }
                    .ifBlank { null }
                    ?.toInt()
                if (value == null) {
                    _state.value = _state.value.copy(
                        hoursFreq = null,
                    )
                } else if (value <= 24) {
                    if (value == 24) {
                        _state.value = _state.value.copy(
                            hoursFreq = value,
                            minutesFreq = 0,
                        )
                    } else {
                        _state.value = _state.value.copy(
                            hoursFreq = value,
                        )
                    }
                }
            }
            
            is WeeklyAsaqEvent.EnteredMinutesFreq -> {
                val value = event.value
                    .filter { it.isDigit() }
                    .ifBlank { null }
                    ?.toInt()
                if (value == null) {
                    _state.value = _state.value.copy(
                        minutesFreq = null,
                    )
                } else if (value <= 59) {
                    val hoursFreq = _state.value.hoursFreq ?: 0
                    if (hoursFreq == 24) {
                        _state.value = _state.value.copy(
                            hoursFreq = 23,
                            minutesFreq = value,
                        )
                    } else {
                        _state.value = _state.value.copy(
                            minutesFreq = value,
                        )
                    }
                }
            }
            
            is WeeklyAsaqEvent.NextQuestion -> {
                val nextQuestionNumber = event.currentQuestion.ordinal + 1
                val nextQuestion = AsaqQuestions.values().first {
                    it.ordinal == nextQuestionNumber
                }
                _state.value = _state.value.copy(
                    currentQuestion = nextQuestion,
                    isFirstQuestion = false,
                    isLastQuestion = nextQuestionNumber == AsaqQuestions.values().lastIndex,
                )
            }
            
            is WeeklyAsaqEvent.PreviousQuestion -> {
                val previousQuestionNumber = event.currentQuestion.ordinal - 1
                val previousQuestion = AsaqQuestions.values().first {
                    it.ordinal == previousQuestionNumber
                }
                val freq = _state.value.responseList.firstOrNull { response ->
                    response.questionId == previousQuestion.questionId
                }?.freq
                _state.value = _state.value.copy(
                    currentQuestion = previousQuestion,
                    hoursFreq = freq?.div(60),
                    minutesFreq = freq?.rem(60),
                    isFirstQuestion = previousQuestionNumber == 0,
                    isLastQuestion = false,
                )
            }
    
            is WeeklyAsaqEvent.SubmitResponse -> {
                viewModelScope.launch {
                    val hoursFreq = event.hoursFreq ?: 0
                    val minutesFreq = event.minutesFreq ?: 0
                    submitAsaqResponseUseCase(
                        programId = event.programId,
                        questionId = event.currentQuestion.questionId,
                        freq = hoursFreq * 60 + minutesFreq,
                    )
                }
            }
            
            is WeeklyAsaqEvent.CompleteAsaq -> {
                viewModelScope.launch {
                    completeProgramUseCase(
                        programId = event.programId,
                    )
                }
            }
        }
    }
    
}