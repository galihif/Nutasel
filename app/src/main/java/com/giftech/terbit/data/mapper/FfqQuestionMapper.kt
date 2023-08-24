package com.giftech.terbit.data.mapper

import com.giftech.terbit.data.source.local.room.entity.FfqFoodEntity
import com.giftech.terbit.data.source.local.room.entity.FfqResponseEntity
import com.giftech.terbit.data.source.local.statics.util.FfqFrequency
import com.giftech.terbit.domain.model.FfqQuestion
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FfqQuestionMapper @Inject constructor() {
    
    fun mapToDomain(
        programId: Int,
        input1: List<FfqFoodEntity>,
        input2: List<FfqResponseEntity>,
    ): List<FfqQuestion> {
        return input1.map { entity ->
            mapToDomain(
                programId = programId,
                input1 = entity,
                input2 = input2,
            )
        }
    }
    
    private fun mapToDomain(
        programId: Int,
        input1: FfqFoodEntity,
        input2: List<FfqResponseEntity>,
    ): FfqQuestion {
        return FfqQuestion(
            programId = programId,
            foodId = input1.foodId,
            foodName = input1.name,
            foodCategoryId = input1.foodCategoryId,
            freq = input2.firstOrNull {
                it.programId == programId && it.foodId == input1.foodId
            }.let {
                when (it?.freq) {
                    FfqFrequency.DAY_1 -> com.giftech.terbit.domain.enums.FfqFrequency.DAY_1
                    FfqFrequency.DAY_3 -> com.giftech.terbit.domain.enums.FfqFrequency.DAY_3
                    FfqFrequency.WEEK_1_2 -> com.giftech.terbit.domain.enums.FfqFrequency.WEEK_1_2
                    FfqFrequency.WEEK_3_6 -> com.giftech.terbit.domain.enums.FfqFrequency.WEEK_3_6
                    FfqFrequency.MONTH_2 -> com.giftech.terbit.domain.enums.FfqFrequency.MONTH_2
                    FfqFrequency.NEVER -> com.giftech.terbit.domain.enums.FfqFrequency.NEVER
                    else -> null
                }
            },
        )
    }
    
}