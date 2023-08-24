package com.giftech.terbit.ui.pages.ffq.main

import com.giftech.terbit.domain.model.FfqFoodCategory

data class FfqMainState(
    val programId: Int = -1,
    val foodCategoryList: List<FfqFoodCategory> = emptyList(),
    val isAllAnswered: Boolean = false,
)