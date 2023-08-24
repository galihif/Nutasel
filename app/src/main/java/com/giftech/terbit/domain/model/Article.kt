package com.giftech.terbit.domain.model

data class Article(
    val programId: Int,
    val articleId: Int,
    val title: String,
    val content: String,
    val imageUrl: String,
    val readingMinutes: Int,
)