package com.giftech.terbit.presentation.ui.pages.notificationinbox

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftech.terbit.domain.usecase.GetEligibleUserNotificationListUseCase
import com.giftech.terbit.domain.usecase.MarkUserNotificationAsReadUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationInboxViewModel @Inject constructor(
    private val getEligibleUserNotificationListUseCase: GetEligibleUserNotificationListUseCase,
    private val markUserNotificationAsReadUseCase: MarkUserNotificationAsReadUseCase,
) : ViewModel() {
    
    private val _state = mutableStateOf(
        NotificationInboxState(
            notificationList = emptyList(),
            isEmpty = false,
        )
    )
    val state: State<NotificationInboxState> = _state
    
    init {
        getNotificationList()
    }
    
    private fun getNotificationList() {
        viewModelScope.launch {
            getEligibleUserNotificationListUseCase().collect { userNotificationList ->
                _state.value = NotificationInboxState(
                    notificationList = userNotificationList,
                    isEmpty = userNotificationList.isEmpty(),
                )
            }
        }
    }
    
    fun onEvent(event: NotificationInboxEvent) {
        when (event) {
            is NotificationInboxEvent.MarkAsRead -> {
                viewModelScope.launch {
                    markUserNotificationAsReadUseCase(
                        notificationId = event.notificationId,
                        idLink = event.idLink,
                    )
                }
            }
        }
    }
    
}