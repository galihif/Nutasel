package com.giftech.terbit.data.source.local.room.entity

import androidx.room.Entity

@Entity(
    tableName = "AsaqEntity",
    primaryKeys = ["questionId", "testType"]
)
data class AsaqEntity(
    val questionId: Int,
    val testType: Int, // 0 for pre-test, 1 for post-test
    val durasiHariKerja: Int,
    val durasiHariLibur: Int,
)
