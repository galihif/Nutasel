package com.giftech.terbit.presentation.ui.pages.ffq.list

import com.giftech.terbit.domain.model.FfqFoodCategory
import com.giftech.terbit.domain.model.FfqQuestion

data class FfqListState(
    val programId: Int,
    val selectedFoodCategory: FfqFoodCategory?,
    val questionListBySelectedFoodCategory: List<FfqQuestion>,
)