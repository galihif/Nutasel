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
    var isComplete: Boolean = false,
) {
    
    constructor(programId: Int, isComplete: Boolean) : this(
        programId = programId,
        week = null,
        dayOfWeek = null,
        type = "",
        isComplete = isComplete,
    )
    
}