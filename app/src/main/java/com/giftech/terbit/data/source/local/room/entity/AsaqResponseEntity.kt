package com.giftech.terbit.data.source.local.room.entity

import androidx.room.Entity

@Entity(
    tableName = "AsaqResponseEntity",
    primaryKeys = ["programId", "questionId"]
)
data class AsaqResponseEntity(
    val programId: Int,
    val questionId: Int,
    val freq: Int,
)