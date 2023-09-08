package com.giftech.terbit.data.source.local.statics

import com.giftech.terbit.data.source.local.room.entity.ProgramEntity
import com.giftech.terbit.domain.enums.ProgramTag
import com.giftech.terbit.domain.enums.ProgramType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProgramData @Inject constructor() {
    
    fun getAll(): List<ProgramEntity> {
        val list = mutableListOf<ProgramEntity>()
        for (i in programIdList.indices) {
            list.add(
                ProgramEntity(
                    programId = programIdList[i],
                    week = weekList[i],
                    dayOfWeek = dayOfWeek[i],
                    type = typeList[i],
                    tag = tagList[i],
                )
            )
        }
        return list
    }
    
    private val programIdList = listOf(
        1, 2, // First time use
        3, 4, 5, 6, 7, 8, 9, 10, 11, // Week 1
        12, 13, 14, 15, 16, 17, 18, 19, 20, // Week 2
        21, 22, 23, 24, 25, 26, 27, 28, 29, // Week 3
        30, 31, 32, 33, 34, 35, 36, 37, 38, // Week 4
        39, 40, // Last
    )
    
    private val weekList = listOf(
        null, null, // First time use
        1, 1, 1, 1, 1, 1, 1, 1, 1,
        2, 2, 2, 2, 2, 2, 2, 2, 2,
        3, 3, 3, 3, 3, 3, 3, 3, 3,
        4, 4, 4, 4, 4, 4, 4, 4, 4,
        null, null, // Last
    )
    
    private val dayOfWeek = listOf(
        null, null, // First time use
        1, 2, 3, 4, 5, 6, 7, 3, 7, // Week 1
        1, 2, 3, 4, 5, 6, 7, 3, 7, // Week 2
        1, 2, 3, 4, 5, 6, 7, 3, 7, // Week 3
        1, 2, 3, 4, 5, 6, 7, 3, 7, // Week 4
        null, null, // Last
    )
    
    private val typeList = listOf(
        // First time use
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_FFQ.typeId,
        // Week 1
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.READ_ARTICLE.typeId,
        ProgramType.READ_ARTICLE.typeId,
        // Week 2
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.READ_ARTICLE.typeId,
        ProgramType.READ_ARTICLE.typeId,
        // Week 3
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.READ_ARTICLE.typeId,
        ProgramType.READ_ARTICLE.typeId,
        // Week 4
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.READ_ARTICLE.typeId,
        ProgramType.READ_ARTICLE.typeId,
        // Last
        ProgramType.FILL_OUT_ASAQ.typeId,
        ProgramType.FILL_OUT_FFQ.typeId,
    )
    
    private val tagList = listOf(
        // First time use
        ProgramTag.PRE_TEST.tagId,
        ProgramTag.PRE_TEST.tagId,
        // Week 1
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        // Week 2
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        // Week 3
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        // Week 4
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        ProgramTag.WEEKLY_PROGRAM.tagId,
        // Last
        ProgramTag.POST_TEST.tagId,
        ProgramTag.POST_TEST.tagId,
    )
    
}