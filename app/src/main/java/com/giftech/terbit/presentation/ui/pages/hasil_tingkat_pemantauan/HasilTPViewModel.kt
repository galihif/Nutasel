package com.giftech.terbit.presentation.ui.pages.hasil_tingkat_pemantauan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftech.terbit.domain.enums.SedenterType
import com.giftech.terbit.domain.usecase.AsaqUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HasilTPViewModel
@Inject constructor(
    private val asaqUseCase: AsaqUseCase
) : ViewModel() {

    private val _sedenterType = MutableStateFlow(SedenterType.RINGAN)
    val sedenterType:StateFlow<SedenterType> = _sedenterType

    private val _avgHours = MutableStateFlow(0.0)
    val avgHours:StateFlow<Double> = _avgHours

    private fun getAvgHours(){
        viewModelScope.launch {
            asaqUseCase.getAsaqAverage().collect {
                _avgHours.value = it
            }
        }
    }

    private fun getSedenterType() {
        viewModelScope.launch {
            asaqUseCase.getSedenterType().collect {
                _sedenterType.value = it
            }
        }
    }

    init {
        getSedenterType()
        getAvgHours()
    }

}