package com.giftech.terbit.ui.pages.asaq.prepost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftech.terbit.data.utils.DataProvider
import com.giftech.terbit.domain.model.Asaq
import com.giftech.terbit.domain.usecase.AsaqUseCase
import com.giftech.terbit.domain.usecase.CompleteProgramUseCase
import com.giftech.terbit.ui.components.enums.AsaqQuestions
import com.giftech.terbit.ui.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AsaqViewModel
@Inject constructor(
    private val asaqUseCase: AsaqUseCase,
    private val completeProgramUseCase: CompleteProgramUseCase,
) : ViewModel() {
    
    var isPreTest: Boolean = true
    var programId: Int = -1
    
    private var _listAsaq = MutableStateFlow(DataProvider.asaqList())
    
    private var _currentNumber = MutableStateFlow(1)
    val currentNumber = _currentNumber
    
    private var _currentAsaq =
        MutableStateFlow(_listAsaq.value.find { it.questionId == _currentNumber.value }!!)
    val currentAsaq = _currentAsaq
    
    private var _currentQuestion = MutableStateFlow(AsaqQuestions.values()[_currentNumber.value])
    val currentQuestion = _currentQuestion
    
    fun prevQuestion() {
        if (_currentNumber.value > 1) {
            _currentNumber.value -= 1
            changeQuestionAnswer()
        }
    }
    
    private fun changeQuestionAnswer() {
        _currentAsaq.value = _listAsaq.value.find { it.questionId == _currentNumber.value }!!
        _currentQuestion.value =
            AsaqQuestions.values().find { it.questionId == _currentNumber.value }!!
    }
    
    fun nextQuestion(newAsaq: Asaq) {
        _listAsaq.value.find { it.questionId == newAsaq.questionId }?.let {
            it.durasiHariKerja = newAsaq.durasiHariKerja
            it.durasiHariLibur = newAsaq.durasiHariLibur
        }
        if (_currentNumber.value < Constants.TOTAL_ASAQ) {
            _currentNumber.value += 1
            changeQuestionAnswer()
        }
    }
    
    fun saveAsaq() {
        viewModelScope.launch {
            if (isPreTest) {
                asaqUseCase.insertPreTestAsaq(_listAsaq.value)
            } else {
                asaqUseCase.insertPostTestAsaq(_listAsaq.value)
            }
        }
        viewModelScope.launch {
            completeProgramUseCase(
                programId = programId,
            )
        }
    }
    
}