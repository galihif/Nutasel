package com.giftech.terbit.presentation.ui.pages.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftech.terbit.domain.model.User
import com.giftech.terbit.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    val _user = MutableStateFlow<User>(User())
    val user = _user

    var _nama = MutableStateFlow("")
    val nama = _nama

    var _tinggi = MutableStateFlow("")
    val tinggi = _tinggi

    var _berat = MutableStateFlow("")
    val berat = _berat

    var _tglLahir = MutableStateFlow("")
    val tglLahir = _tglLahir
    fun getUser() {
        viewModelScope.launch {
            userUseCase.getUser().collect{
                _user.value = it
                _nama.value = it.nama
                _tinggi.value = it.tinggi.toString()
                _berat.value = it.berat.toString()
                _tglLahir.value = it.tglLahir
            }
        }
    }

    fun saveUser() {
        viewModelScope.launch {
            userUseCase.saveUser(
                _user.value.copy(
                    nama = _nama.value,
                    tinggi = _tinggi.value.toInt(),
                    berat = _berat.value.toInt(),
                    tglLahir = _tglLahir.value
                )
            )
        }
    }

    init {
        getUser()
    }

}