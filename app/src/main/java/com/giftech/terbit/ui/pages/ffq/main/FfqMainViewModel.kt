package com.giftech.terbit.ui.pages.ffq.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftech.terbit.domain.usecase.CheckAllFfqQuestionAnsweredUseCase
import com.giftech.terbit.domain.usecase.CompleteProgramUseCase
import com.giftech.terbit.domain.usecase.GetFfqFoodCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FfqMainViewModel @Inject constructor(
    private val checkAllFfqQuestionAnsweredUseCase: CheckAllFfqQuestionAnsweredUseCase,
    private val completeProgramUseCase: CompleteProgramUseCase,
    private val getFfqFoodCategoryUseCase: GetFfqFoodCategoryUseCase,
) : ViewModel() {
    
    private val _state = mutableStateOf(
        FfqMainState(
            programId = -1,
            foodCategoryList = emptyList(),
            isAllAnswered = false,
        )
    )
    val state: State<FfqMainState> = _state
    
    init {
        getFoodCategoryList()
    }
    
    private fun getFoodCategoryList() {
        viewModelScope.launch {
            getFfqFoodCategoryUseCase().also { foodCategoryList ->
                _state.value = _state.value.copy(
                    foodCategoryList = foodCategoryList,
                )
            }
        }
    }
    
    fun onEvent(event: FfqMainEvent) {
        when (event) {
            is FfqMainEvent.Init -> {
                viewModelScope.launch {
                    checkAllFfqQuestionAnsweredUseCase(event.programId)
                        .collect { isAllAnswered ->
                            _state.value = _state.value.copy(
                                programId = event.programId,
                                isAllAnswered = isAllAnswered,
                            )
                        }
                }
            }
            
            is FfqMainEvent.CompleteFfq -> {
                viewModelScope.launch {
                    completeProgramUseCase(
                        programId = event.programId,
                    )
                }
            }
        }
    }
    
}