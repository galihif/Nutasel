package com.giftech.terbit.presentation.ui.pages.hasil_imt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftech.terbit.domain.model.User
import com.giftech.terbit.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HasilIMTViewModel
@Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    val _user = MutableStateFlow<User>(User())
    val user = _user

    fun getUser() {
        viewModelScope.launch {
            userUseCase.getUser().collect {
                _user.value = it
            }
        }
    }

    init {
        getUser()
    }

}