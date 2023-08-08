package com.giftech.terbit.data.source.local.statics.model

import androidx.annotation.DrawableRes

data class FfqFoodCategoryEntity(
    val categoryId: Int,
    val name: String,
    @DrawableRes val image: Int,
)