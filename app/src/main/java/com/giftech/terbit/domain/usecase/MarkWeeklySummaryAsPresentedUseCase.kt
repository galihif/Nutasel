package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.repository.IWeeklySummaryRepository
import javax.inject.Inject

class MarkWeeklySummaryAsPresentedUseCase @Inject constructor(
    private val weeklySummaryRepository: IWeeklySummaryRepository,
) {
    
    suspend operator fun invoke(
        week: Int,
    ) {
        weeklySummaryRepository.updateHasPresented(
            week = week,
            hasPresented = true,
        )
    }
    
}