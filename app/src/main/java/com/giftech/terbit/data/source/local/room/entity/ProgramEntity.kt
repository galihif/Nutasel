package com.giftech.terbit.data.source.local.room.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "ProgramEntity")
data class ProgramEntity(
    @PrimaryKey val programId: Int,
    @Ignore val week: Int?,
    @Ignore val dayOfWeek: Int?,
    @Ignore val type: String,
    var isCompleted: Boolean = false,
    var completionDateInMillis: Long? = null,
    @Ignore val tag: String,
) {
    
    constructor(programId: Int, isCompleted: Boolean, completionDateInMillis: Long?) : this(
        programId = programId,
        week = null,
        dayOfWeek = null,
        type = "",
        isCompleted = isCompleted,
        completionDateInMillis = completionDateInMillis,
        tag = "",
    )
    
}