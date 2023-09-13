package com.giftech.terbit.ui.pages.ffq.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftech.terbit.domain.usecase.AddFfqFoodUseCase
import com.giftech.terbit.domain.usecase.GetFfqFoodCategoryUseCase
import com.giftech.terbit.domain.usecase.GetFfqQuestionListByFoodCategoryUseCase
import com.giftech.terbit.domain.usecase.SubmitFfqResponseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FfqListViewModel @Inject constructor(
    private val addFfqFoodUseCase: AddFfqFoodUseCase,
    private val getFfqFoodCategoryUseCase: GetFfqFoodCategoryUseCase,
    private val getFfqQuestionListByFoodCategoryUseCase: GetFfqQuestionListByFoodCategoryUseCase,
    private val submitFfqResponseUseCase: SubmitFfqResponseUseCase,
) : ViewModel() {
    
    private val _state = mutableStateOf(
        FfqListState(
            programId = -1,
            selectedFoodCategory = null,
            questionListBySelectedFoodCategory = emptyList(),
        )
    )
    val state: State<FfqListState> = _state
    
    fun onEvent(event: FfqListEvent) {
        when (event) {
            is FfqListEvent.Init -> {
                viewModelScope.launch {
                    getFfqQuestionListByFoodCategoryUseCase(
                        programId = event.programId,
                        foodCategoryId = event.foodCategoryId,
                    ).collect { questionList ->
                        _state.value = _state.value.copy(
                            programId = event.programId,
                            questionListBySelectedFoodCategory = questionList,
                        )
                    }
                }
                
                getFfqFoodCategoryUseCase().also { foodCategoryList ->
                    _state.value = _state.value.copy(
                        selectedFoodCategory = foodCategoryList.first {
                            it.foodCategoryId == event.foodCategoryId
                        },
                    )
                }
            }
            
            is FfqListEvent.AddNewFood -> {
                if (event.foodName.isNotEmpty()) {
                    viewModelScope.launch {
                        addFfqFoodUseCase(
                            foodName = event.foodName,
                            foodCategoryId = event.foodCategoryId,
                        )
                    }
                }
            }
            
            is FfqListEvent.SubmitResponse -> {
                viewModelScope.launch {
                    submitFfqResponseUseCase(
                        programId = event.programId,
                        foodId = event.foodId,
                        freq = event.freq,
                    )
                }
            }
        }
    }
    
}