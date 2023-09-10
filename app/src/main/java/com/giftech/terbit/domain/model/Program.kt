package com.giftech.terbit.domain.model

import com.giftech.terbit.domain.enums.ProgramTag

sealed interface Program {
    val programId: Int
    val week: Int?
    val dayOfWeek: Int?
    val isComplete: Boolean
    val completionDateInMillis: Long?
    val tag: ProgramTag
}

class FillOutAsaq(
    override val programId: Int,
    override val week: Int?,
    override val dayOfWeek: Int?,
    override val isComplete: Boolean,
    override val completionDateInMillis: Long?,
    override val tag: ProgramTag,
) : Program

class FillOutFfq(
    override val programId: Int,
    override val week: Int?,
    override val dayOfWeek: Int?,
    override val isComplete: Boolean,
    override val completionDateInMillis: Long?,
    override val tag: ProgramTag,
) : Program

class ReadArticle(
    override val programId: Int,
    override val week: Int?,
    override val dayOfWeek: Int?,
    override val isComplete: Boolean,
    override val completionDateInMillis: Long?,
    override val tag: ProgramTag,
) : Program