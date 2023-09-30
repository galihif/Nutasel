package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.model.Notification
import com.giftech.terbit.domain.repository.INotificationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetDailyNotificationListUseCase @Inject constructor(
    private val notificationRepository: INotificationRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(): Flow<List<Notification>> {
        return notificationRepository.getAll()
            .mapLatest { notificationList ->
                notificationList.filter {
                    it.type == com.giftech.terbit.domain.enums.NotificationType.DAILY_TIPS
                }
            }
            .flowOn(Dispatchers.IO)
    }
    
}