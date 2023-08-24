package com.giftech.terbit.domain.model

import com.giftech.terbit.domain.enums.FfqFrequency

data class FfqQuestion(
    val programId: Int,
    val foodId: Int,
    val foodName: String,
    val foodCategoryId: Int,
    var freq: FfqFrequency? = null,
)