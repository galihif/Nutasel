package com.giftech.terbit.data.mapper

import com.giftech.terbit.data.source.local.room.entity.AsaqResponseEntity
import com.giftech.terbit.domain.model.AsaqResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AsaqResponseMapper @Inject constructor() {
    
    fun mapToDomain(input: List<AsaqResponseEntity>): List<AsaqResponse> {
        return input.map { entity ->
            mapToDomain(entity)
        }
    }
    
    private fun mapToDomain(input: AsaqResponseEntity): AsaqResponse {
        return AsaqResponse(
            programId = input.programId,
            questionId = input.questionId,
            freq = input.freq,
        )
    }
    
}