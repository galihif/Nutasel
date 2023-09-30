package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.model.UserNotification
import com.giftech.terbit.domain.repository.IUserNotificationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetEligibleUserNotificationListUseCase @Inject constructor(
    private val userNotificationRepository: IUserNotificationRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(): Flow<List<UserNotification>> {
        return userNotificationRepository.getAll()
            .mapLatest { userNotificationList ->
                userNotificationList
                    .filter {
                        it.activeStatus && it.shownStatus
                    }
                    .sortedByDescending {
                        it.triggerDateTimeInMillis
                    }
            }
            .flowOn(Dispatchers.IO)
    }
    
}