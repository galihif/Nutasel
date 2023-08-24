package com.giftech.terbit.domain.model

data class AsaqQuestion(
    val programId: Int,
    val questionId: Int,
    val number: Int,
    val headline: String,
    val question: String,
    var freq: Int? = null,
    val imageRes: Int,
)