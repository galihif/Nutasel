package com.giftech.terbit.data.source.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FfqAnswerEntity")
data class FfqAnswerEntity(
    @PrimaryKey val answerId: Int,
    val foodId: Int,
    val freq: String,
    val programId: Int,
)