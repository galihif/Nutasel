package com.giftech.terbit.domain.model

sealed interface Program {
    val programId: Int
    val week: Int?
    val dayOfWeek: Int?
    val isComplete: Boolean
}

class FillOutAsaq(
    override val programId: Int,
    override val week: Int?,
    override val dayOfWeek: Int?,
    override val isComplete: Boolean,
) : Program

class FillOutFfq(
    override val programId: Int,
    override val week: Int?,
    override val dayOfWeek: Int?,
    override val isComplete: Boolean,
) : Program

class ReadArticle(
    override val programId: Int,
    override val week: Int?,
    override val dayOfWeek: Int?,
    override val isComplete: Boolean,
) : Program