package com.giftech.terbit.data.mapper

import com.giftech.terbit.data.source.local.statics.model.ArticleEntity
import com.giftech.terbit.domain.model.Article
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleMapper @Inject constructor() {

    fun mapToDomain (input: List<ArticleEntity>): List<Article> {
        return input.map { entity ->
            mapToDomain(entity)
        }
    }

    fun mapToDomain(input: ArticleEntity): Article {
        return Article(
            articleId = input.articleId,
            week = input.week,
            day = input.day,
            title = input.title,
            imageRes = input.imageRes,
            readDuration = input.readDuration,
            category = input.category,
            content = input.content,
            reference = input.reference,
            imageSource = input.imageSource,
        )
    }
}