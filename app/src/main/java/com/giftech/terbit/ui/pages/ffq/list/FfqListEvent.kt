package com.giftech.terbit.ui.pages.ffq.list

import com.giftech.terbit.domain.enums.FfqFrequency

sealed class FfqListEvent {
    
    data class Init(
        val programId: Int,
        val foodCategoryId: Int,
    ) : FfqListEvent()
    
    data class SubmitResponse(
        val programId: Int,
        val foodId: Int,
        val freq: FfqFrequency,
    ) : FfqListEvent()
    
    data class AddNewFood(
        val foodName: String,
        val foodCategoryId: Int,
    ) : FfqListEvent()
    
}