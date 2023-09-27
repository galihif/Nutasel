package com.giftech.terbit.data.source.local.statics.model

import androidx.annotation.DrawableRes

data class FfqFoodCategoryEntity(
    val foodCategoryId: Int,
    val name: String,
    val abbreviation: String,
    @DrawableRes val imageRes: Int,
)