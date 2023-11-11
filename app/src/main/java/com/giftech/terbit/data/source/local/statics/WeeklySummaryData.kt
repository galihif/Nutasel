package com.giftech.terbit.data.source.local.statics

import com.giftech.terbit.data.source.local.statics.model.WeeklySummaryStatic
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeeklySummaryData @Inject constructor() {
    
    fun getAll(): List<WeeklySummaryStatic> {
        val list = mutableListOf<WeeklySummaryStatic>()
        for (i in weekList.indices) {
            list.add(
                WeeklySummaryStatic(
                    week = weekList[i],
                )
            )
        }
        return list
    }
    
    private val weekList = listOf(
        1,
        2,
        3,
    )
    
}