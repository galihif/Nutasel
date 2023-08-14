package com.giftech.terbit.ui.pages.asaq

import androidx.lifecycle.ViewModel
import com.giftech.terbit.data.model.Asaq
import com.giftech.terbit.data.utils.DataProvider
import com.giftech.terbit.ui.utils.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AsaqViewModel
@Inject constructor(

) : ViewModel() {

    private var _listAsaq = MutableStateFlow(DataProvider.asaqList())

    private var _currentNumber = MutableStateFlow(1)
    val currentNumber = _currentNumber

    private var _currentAsaq = MutableStateFlow(_listAsaq.value.find { it.id == _currentNumber.value }!!)
    val currentAsaq = _currentAsaq

    private var _totalScore = MutableStateFlow(0)
    val totalScore = _totalScore

    fun prevQuestion() {
        if (_currentNumber.value > 1) {
            _currentNumber.value -= 1
            _currentAsaq.value = _listAsaq.value.find { it.id == _currentNumber.value }!!
        }
    }

    fun nextQuestion(newAsaq: Asaq) {
        _listAsaq.value.find { it.id == newAsaq.id }?.let {
            it.tingkatHariKerja = newAsaq.tingkatHariKerja
            it.tingkatHariLibur = newAsaq.tingkatHariLibur
        }
        if (_currentNumber.value < Constant.TOTAL_ASAQ) {
            _currentNumber.value += 1
            _currentAsaq.value = _listAsaq.value.find { it.id == _currentNumber.value }!!
        }
    }

    fun calculateScore(){
        var totalScore = 0
        _listAsaq.value.forEach {
            totalScore += it.tingkatHariKerja.score
            totalScore += it.tingkatHariLibur.score
        }
        _totalScore.value = totalScore
    }
}