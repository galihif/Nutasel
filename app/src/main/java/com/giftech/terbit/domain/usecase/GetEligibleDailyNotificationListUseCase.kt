package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.enums.NotificationType
import com.giftech.terbit.domain.enums.ProgramTag
import com.giftech.terbit.domain.model.Notification
import com.giftech.terbit.domain.repository.INotificationRepository
import com.giftech.terbit.domain.repository.IProgramRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetEligibleDailyNotificationListUseCase @Inject constructor(
    private val notificationRepository: INotificationRepository,
    private val programRepository: IProgramRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(): Flow<List<Notification>> {
        return programRepository.getAll()
            .flatMapLatest { programList ->
                notificationRepository.getAll()
                    .mapLatest { notificationList ->
                        val preTestProgramList = programList.filter {
                            it.tag == ProgramTag.PRE_TEST
                        }
                        val weeklyProgramList = programList.filter {
                            it.tag == ProgramTag.WEEKLY_PROGRAM
                        }
                        val isPreTestDone = preTestProgramList.all { it.isCompleted }
                        val isWeeklyProgramDone = weeklyProgramList.all { it.isCompleted }
                        
                        notificationList.filter {
                            when (it.id) {
                                6000 -> isPreTestDone && !isWeeklyProgramDone
                                else -> it.type == NotificationType.DAILY_TIPS
                            }
                        }
                        
                    }
                    .flowOn(Dispatchers.IO)
            }
    }
    
}