package com.giftech.terbit.presentation.ui.pages.ffq.main

import com.giftech.terbit.domain.model.FfqFoodCategory

data class FfqMainState(
    val programId: Int,
    val foodCategoryList: List<FfqFoodCategory>,
    val completionStatusPerFoodCategory: Map<Int, Boolean>,
    val isAllAnswered: Boolean,
)