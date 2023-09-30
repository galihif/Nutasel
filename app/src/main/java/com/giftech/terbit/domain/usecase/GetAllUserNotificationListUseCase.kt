package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.model.UserNotification
import com.giftech.terbit.domain.repository.IUserNotificationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllUserNotificationListUseCase @Inject constructor(
    private val userNotificationRepository: IUserNotificationRepository,
) {
    
    suspend operator fun invoke(): Flow<List<UserNotification>> {
        return userNotificationRepository.getAll()
            .flowOn(Dispatchers.IO)
    }
    
}