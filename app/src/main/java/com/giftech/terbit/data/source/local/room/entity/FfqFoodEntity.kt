package com.giftech.terbit.data.source.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FfqFoodEntity")
data class FfqFoodEntity(
    @PrimaryKey val foodId: Int,
    val categoryId: Int,
    val name: String,
)