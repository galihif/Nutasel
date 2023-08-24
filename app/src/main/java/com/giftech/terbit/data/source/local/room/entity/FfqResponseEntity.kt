package com.giftech.terbit.data.source.local.room.entity

import androidx.room.Entity

@Entity(
    tableName = "FfqResponseEntity",
    primaryKeys = ["programId", "foodId"],
)
data class FfqResponseEntity(
    val programId: Int,
    val foodId: Int,
    val freq: String,
)