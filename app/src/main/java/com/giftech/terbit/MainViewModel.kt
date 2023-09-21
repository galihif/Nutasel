package com.giftech.terbit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.giftech.terbit.domain.model.UserNotification
import com.giftech.terbit.domain.usecase.GetAllUserNotificationListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllUserNotificationUseCase: GetAllUserNotificationListUseCase,
): ViewModel(){

    fun  getAllUserNotification(): LiveData<List<UserNotification>> {
        return getAllUserNotificationUseCase().asLiveData()
    }

}