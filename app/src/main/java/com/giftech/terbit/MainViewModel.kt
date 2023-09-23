package com.giftech.terbit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.giftech.terbit.domain.model.Notification
import com.giftech.terbit.domain.model.UserNotification
import com.giftech.terbit.domain.usecase.GetAllUserNotificationListUseCase
import com.giftech.terbit.domain.usecase.GetDailyNotificationListUseCase
import com.giftech.terbit.domain.usecase.MonitorNotificationUseCase
import com.giftech.terbit.domain.usecase.UpdateSchedulingStatusUserNotificationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllUserNotificationUseCase: GetAllUserNotificationListUseCase,
    private val monitorNotificationUseCase: MonitorNotificationUseCase,
    private val updateSchedulingStatusUserNotificationUseCase: UpdateSchedulingStatusUserNotificationUseCase,
    private val getDailyNotificationListUseCase: GetDailyNotificationListUseCase,
): ViewModel(){
    
    init {
        monitoringNotification()
    }
    
    private fun monitoringNotification() {
        viewModelScope.launch {
            monitorNotificationUseCase().collect()
        }
    }

    fun  getAllUserNotification(): LiveData<List<UserNotification>> {
        return getAllUserNotificationUseCase().asLiveData()
    }
    
    fun updateSchedulingStatusUserNotification(userNotification: UserNotification) {
        viewModelScope.launch {
            updateSchedulingStatusUserNotificationUseCase(
                notificationId = userNotification.notificationId,
                idLink = userNotification.idLink,
            )
        }
    }
    
    fun getDailyNotificationList(): List<Notification> {
        return getDailyNotificationListUseCase()
    }

}