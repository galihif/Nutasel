package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.model.Notification
import com.giftech.terbit.domain.repository.INotificationRepository
import javax.inject.Inject

class GetAllNotificationListUseCase @Inject constructor(
    private val notificationRepository: INotificationRepository,
){
    
    operator fun invoke(): List<Notification> {
        return notificationRepository.getAll()
    }
    
}