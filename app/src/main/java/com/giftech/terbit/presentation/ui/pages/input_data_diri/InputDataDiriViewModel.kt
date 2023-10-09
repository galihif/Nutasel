package com.giftech.terbit.presentation.ui.pages.input_data_diri

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftech.terbit.domain.model.User
import com.giftech.terbit.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputDataDiriViewModel
@Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private var _nama: MutableStateFlow<String> = MutableStateFlow("")
    val nama = _nama

    private var _tinggi: MutableStateFlow<String> = MutableStateFlow("")
    val tinggi = _tinggi

    private var _berat: MutableStateFlow<String> = MutableStateFlow("")
    val berat = _berat

    private var _tglLahir: MutableStateFlow<String> = MutableStateFlow("")
    val tglLahir = _tglLahir

    private var _isMale: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isMale = _isMale

    fun saveUser() {
        val user = User(
            nama = _nama.value,
            tinggi = _tinggi.value.trim().toInt(),
            berat = _berat.value.trim().toInt(),
            tglLahir = _tglLahir.value,
            isMale = _isMale.value
        )
        viewModelScope.launch {
            userUseCase.saveUser(user)
        }
    }

    private fun getUser() {
        viewModelScope.launch {
            userUseCase.getUser().collect {
                _nama.value = it.nama
                _tinggi.value = it.tinggi.toString()
                _berat.value = it.berat.toString()
                _tglLahir.value = it.tglLahir
                _isMale.value = it.isMale
            }
        }
    }

    init {
        getUser()
    }
}