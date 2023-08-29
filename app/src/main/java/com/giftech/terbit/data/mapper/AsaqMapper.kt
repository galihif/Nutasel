package com.giftech.terbit.data.mapper

import com.giftech.terbit.data.source.local.room.entity.AsaqEntity
import com.giftech.terbit.domain.model.Asaq
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AsaqMapper @Inject constructor() {

    fun mapToDomain(input: List<AsaqEntity>): List<Asaq> {
        return input.map { entity ->
            mapToDomain(entity)
        }
    }

    private fun mapToDomain(input: AsaqEntity): Asaq {
        return Asaq(
            questionId = input.questionId,
            durasiHariKerja = input.durasiHariKerja,
            durasiHariLibur = input.durasiHariLibur,
        )
    }

    fun mapToEntity(input: List<Asaq>, testType: Int): List<AsaqEntity> {
        return input.map { entity ->
            mapToEntity(entity, testType)
        }
    }

    private fun mapToEntity(input: Asaq, testType:Int): AsaqEntity {
        return AsaqEntity(
            questionId = input.questionId,
            durasiHariKerja = input.durasiHariKerja,
            durasiHariLibur = input.durasiHariLibur,
            testType = testType,
        )
    }
}