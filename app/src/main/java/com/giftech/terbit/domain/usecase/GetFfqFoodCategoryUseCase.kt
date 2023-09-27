package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.model.FfqFoodCategory
import com.giftech.terbit.domain.repository.IFfqFoodCategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFfqFoodCategoryUseCase @Inject constructor(
    private val ffqFoodCategoryRepository: IFfqFoodCategoryRepository,
) {
    
    operator fun invoke(): Flow<List<FfqFoodCategory>> {
        return ffqFoodCategoryRepository.getAll()
    }
    
}