package com.giftech.terbit.data.source.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FfqFoodEntity")
data class FfqFoodEntity(
    @PrimaryKey(autoGenerate = true) val foodId: Int,
    val foodCategoryId: Int,
    val name: String,
)