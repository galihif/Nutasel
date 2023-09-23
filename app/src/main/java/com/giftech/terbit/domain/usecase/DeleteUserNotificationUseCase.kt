package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.repository.IUserNotificationRepository
import javax.inject.Inject

class DeleteUserNotificationUseCase @Inject constructor(
    private val userNotificationRepository: IUserNotificationRepository,
) {
    
    suspend operator fun invoke(
        notificationId: Int,
        idLink: Int,
    ) {
        userNotificationRepository.delete(
            notificationId = notificationId,
            idLink = idLink,
        )
    }
    
}