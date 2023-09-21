package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.model.UserNotification
import com.giftech.terbit.domain.repository.IUserNotificationRepository
import javax.inject.Inject

class ReadUserNotificationUseCase @Inject constructor(
    private val userNotificationRepository: IUserNotificationRepository,
) {
    
    suspend operator fun invoke(
        userNotificationList: List<UserNotification>,
    ) {
        userNotificationList.forEach {
            userNotificationRepository.updateReadStatus(
                notificationId = it.notificationId,
                idLink = it.idLink,
                readStatus = true,
            )
        }
    }
    
}