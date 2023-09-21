package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.repository.IUserNotificationRepository
import javax.inject.Inject

class AddUserNotificationUseCase @Inject constructor(
    private val userNotificationRepository: IUserNotificationRepository,
) {
    
    suspend operator fun invoke(
        notificationId: Int,
        title: String,
        message: String,
        deepLink: String,
        idLink: Int,
        triggerDateTimeInMillis: Long,
        notificationType: String,
        activeStatus: Boolean,
    ) {
        userNotificationRepository.insert(
            notificationId = notificationId,
            title = title,
            message = message,
            deepLink = deepLink,
            idLink = idLink,
            readStatus = false,
            triggerDateTimeInMillis = triggerDateTimeInMillis,
            notificationType = notificationType,
            activeStatus = activeStatus,
        )
    }
    
}