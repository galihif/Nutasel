package com.giftech.terbit.data.source.local.statics

import com.giftech.terbit.data.source.local.statics.model.ProgramEntity
import com.giftech.terbit.domain.enums.ProgramType

object ProgramData {
    
    fun get(): List<ProgramEntity> {
        val list = mutableListOf<ProgramEntity>()
        for (i in programIdList.indices) {
            list.add(
                ProgramEntity(
                    programId = programIdList[i],
                    week = weekList[i],
                    dayOfWeek = dayOfWeek[i],
                    type = typeList[i],
                )
            )
        }
        return list
    }
    
    private val programIdList = listOf(
        1, 2, // First time use
        3, 4, 5, 6, 7, 8, 9, 10, 11, 12, // Week 1
        13, 14, 15, 16, 17, 18, 19, 20, 21, 22, // Week 2
        23, 24, 25, 26, 27, 28, 29, 30, 31, 32, // Week 3
        33, 34, 35, 36, 37, 38, 39, 40, 41, 42, // Week 4
        43, 44, 45, 46, 47, // Week 5
    )
    
    private val weekList = listOf(
        null, null, // First time use
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
        3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
        4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
        5, 5, 5, 5, 5,
    )
    
    private val dayOfWeek = listOf(
        null, null, // First time use
        1, 2, 3, 4, 5, 6, 7, 3, 7, null, // Week 1
        1, 2, 3, 4, 5, 6, 7, 3, 7, null, // Week 2
        1, 2, 3, 4, 5, 6, 7, 3, 7, null, // Week 3
        1, 2, 3, 4, 5, 6, 7, 3, 7, null, // Week 4
        1, 2, 3, 7, null, // Week 5
    )
    
    private val typeList = listOf(
        // First time use
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_FFQ.typeId,
        // Week 1
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.READ_ARTICLE.typeId,
        ProgramType.READ_ARTICLE.typeId,
        ProgramType.TAKE_FFQ.typeId,
        // Week 2
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.READ_ARTICLE.typeId,
        ProgramType.READ_ARTICLE.typeId,
        ProgramType.TAKE_FFQ.typeId,
        // Week 3
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.READ_ARTICLE.typeId,
        ProgramType.READ_ARTICLE.typeId,
        ProgramType.TAKE_FFQ.typeId,
        // Week 4
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.READ_ARTICLE.typeId,
        ProgramType.READ_ARTICLE.typeId,
        ProgramType.TAKE_FFQ.typeId,
        // Week 5
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.TAKE_ASAQ.typeId,
        ProgramType.READ_ARTICLE.typeId,
        ProgramType.READ_ARTICLE.typeId,
        ProgramType.TAKE_FFQ.typeId,
    )
    
}