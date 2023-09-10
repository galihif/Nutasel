package com.giftech.terbit.ui.pages.ffq.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftech.terbit.domain.usecase.AddFfqFoodUseCase
import com.giftech.terbit.domain.usecase.GetFfqFoodCategoryUseCase
import com.giftech.terbit.domain.usecase.GetFfqQuestionByFoodCategoryUseCase
import com.giftech.terbit.domain.usecase.SubmitFfqResponseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FfqListViewModel @Inject constructor(
    private val addFfqFoodUseCase: AddFfqFoodUseCase,
    private val getFfqFoodCategoryUseCase: GetFfqFoodCategoryUseCase,
    private val getFfqQuestionByFoodCategoryUseCase: GetFfqQuestionByFoodCategoryUseCase,
    private val submitFfqResponseUseCase: SubmitFfqResponseUseCase,
) : ViewModel() {
    
    private val _state = MutableStateFlow(FfqListState())
    val state = _state.asStateFlow()
    
    fun onEvent(event: FfqListEvent) {
        when (event) {
            is FfqListEvent.Init -> {
                viewModelScope.launch {
                    getFfqQuestionByFoodCategoryUseCase(
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