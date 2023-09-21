package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.model.UserNotification
import com.giftech.terbit.domain.repository.IUserNotificationRepository
import com.giftech.terbit.domain.util.toMillis
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import javax.inject.Inject

class GetEligibleUserNotificationListUseCase @Inject constructor(
    private val userNotificationRepository: IUserNotificationRepository,
) {
    
    operator fun invoke(): Flow<List<UserNotification>> {
        return userNotificationRepository.getAll()
            .map { userNotificationList ->
                userNotificationList
                    .filter {
                        it.triggerDateTimeInMillis != null && it.activeStatus
                    }
                    .filter {
                        LocalDateTime.now().toMillis() >= it.triggerDateTimeInMillis!!
                    }
                    .sortedByDescending {
                        it.triggerDateTimeInMillis
                    }
            }
    }
    
}