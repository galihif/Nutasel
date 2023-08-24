package com.giftech.terbit.data.mapper

import com.giftech.terbit.data.source.local.room.entity.ProgramEntity
import com.giftech.terbit.data.source.local.statics.util.ProgramType
import com.giftech.terbit.domain.model.FillOutAsaq
import com.giftech.terbit.domain.model.FillOutFfq
import com.giftech.terbit.domain.model.Program
import com.giftech.terbit.domain.model.ReadArticle
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProgramMapper @Inject constructor() {
    
    fun mapToDomain(input: List<ProgramEntity>): List<Program> {
        return input.map { entity ->
            mapToDomain(entity)
        }
    }
    
    private fun mapToDomain(input: ProgramEntity): Program {
        return when (input.type) {
            ProgramType.FILL_OUT_ASAQ -> {
                FillOutAsaq(
                    programId = input.programId,
                    week = input.week,
                    dayOfWeek = input.dayOfWeek,
                    isComplete = input.isComplete,
                )
            }
            
            ProgramType.FILL_OUT_FFQ -> {
                FillOutFfq(
                    programId = input.programId,
                    week = input.week,
                    dayOfWeek = input.dayOfWeek,
                    isComplete = input.isComplete,
                )
            }
            
            ProgramType.READ_ARTICLE -> {
                ReadArticle(
                    programId = input.programId,
                    week = input.week,
                    dayOfWeek = input.dayOfWeek,
                    isComplete = input.isComplete,
                )
            }
            
            else -> throw IllegalArgumentException("Unknown program type")
        }
    }
    
}