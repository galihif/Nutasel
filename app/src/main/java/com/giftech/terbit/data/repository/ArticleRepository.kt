package com.giftech.terbit.data.repository

import com.giftech.terbit.data.mapper.ArticleMapper
import com.giftech.terbit.data.source.local.ArticleLocalDataSource
import com.giftech.terbit.domain.model.Article
import com.giftech.terbit.domain.repository.IArticleRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(
    private val articleLocalDataSource: ArticleLocalDataSource,
    private val mapper: ArticleMapper
) : IArticleRepository {
    override fun getArticleByWeekDay(week: Int, day: Int): Article? {
        return articleLocalDataSource.getAll().firstOrNull { it.week == week && it.day == day }
            .let {
                if (it != null) mapper.mapToDomain(it) else null
            }
    }

    override fun getArticlesByWeek(week: Int): List<Article> {
        return articleLocalDataSource.getAll().filter { it.week == week }
            .let {
                mapper.mapToDomain(it)
            }
    }

    override fun getArticleById(id: Int): Article? {
        return articleLocalDataSource.getAll().firstOrNull { it.articleId == id }
            .let {
                if (it != null) mapper.mapToDomain(it) else null
            }
    }
}