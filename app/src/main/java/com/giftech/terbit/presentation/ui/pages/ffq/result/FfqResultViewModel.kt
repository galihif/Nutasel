package com.giftech.terbit.presentation.ui.pages.ffq.result

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftech.terbit.domain.usecase.GetFfqResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FfqResultViewModel @Inject constructor(
    private val getFfqResult: GetFfqResultUseCase,
) : ViewModel() {
    
    private val _state = mutableStateOf(
        FfqResultState(
            programId = -1,
            result = null,
        )
    )
    val state: State<FfqResultState> = _state
    
    fun onEvent(event: FfqResultEvent) {
        when (event) {
            is FfqResultEvent.Init -> {
                viewModelScope.launch {
                    getFfqResult(
                        programId = event.programId,
                    ).collect { result ->
                        _state.value = FfqResultState(
                            programId = event.programId,
                            result = result,
                        )
                    }
                }
            }
        }
    }
    
}