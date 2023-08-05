package com.giftech.terbit.ui.pages.input_data_diri

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class InputDataDiriViewModel
@Inject constructor(

) : ViewModel() {

    private var _nama:MutableStateFlow<String> = MutableStateFlow("")
    val nama = _nama

    private var _tinggi:MutableStateFlow<String> = MutableStateFlow("")
    val tinggi = _tinggi

    private var _berat:MutableStateFlow<String> = MutableStateFlow("")
    val berat = _berat

    private var _usia:MutableStateFlow<String> = MutableStateFlow("")
    val usia = _usia

    private var _isMale:MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isMale = _isMale

}