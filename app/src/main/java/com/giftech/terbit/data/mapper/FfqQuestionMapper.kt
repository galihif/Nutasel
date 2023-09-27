package com.giftech.terbit.data.mapper

import com.giftech.terbit.data.source.local.room.entity.FfqFoodEntity
import com.giftech.terbit.data.source.local.room.entity.FfqResponseEntity
import com.giftech.terbit.data.source.local.room.entity.ProgramEntity
import com.giftech.terbit.data.source.local.statics.util.FfqFrequency
import com.giftech.terbit.domain.model.FfqQuestion
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FfqQuestionMapper @Inject constructor() {
    
    fun mapToDomain(
        input1: List<ProgramEntity>,
        input2: List<FfqFoodEntity>,
        input3: List<FfqResponseEntity>,
    ): List<FfqQuestion> {
        val output = mutableListOf<FfqQuestion>()
        input1.forEach { programEntity ->
            input2.forEach { foodEntity ->
                output.add(
                    mapToDomain(
                        programId = programEntity.programId,
                        input2 = foodEntity,
                        input3 = input3,
                    )
                )
            }
        }
        return output
    }
    
    private fun mapToDomain(
        programId: Int,
        input2: FfqFoodEntity,
        input3: List<FfqResponseEntity>,
    ): FfqQuestion {
        return FfqQuestion(
            programId = programId,
            foodId = input2.foodId,
            foodName = input2.name,
            foodCategoryId = input2.foodCategoryId,
            freq = input3.firstOrNull {
                it.programId == programId && it.foodId == input2.foodId
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