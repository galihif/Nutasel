package com.giftech.terbit.ui.pages.ffq.list

import com.giftech.terbit.domain.model.FfqFoodCategory
import com.giftech.terbit.domain.model.FfqQuestion

data class FfqListState(
    val programId: Int = -1,
    val selectedFoodCategory: FfqFoodCategory? = null,
    val questionListBySelectedFoodCategory: List<FfqQuestion> = emptyList(),
)