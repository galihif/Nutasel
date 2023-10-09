package com.giftech.terbit.presentation.ui.pages.notificationinbox

import com.giftech.terbit.domain.model.UserNotification

data class NotificationInboxState(
    val notificationList: List<UserNotification>,
    val isEmpty: Boolean,
)