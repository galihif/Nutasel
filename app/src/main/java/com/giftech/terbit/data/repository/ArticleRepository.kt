package com.giftech.terbit.data.repository

import com.giftech.terbit.data.mapper.ArticleMapper
import com.giftech.terbit.data.source.local.ArticleLocalDataSource
import com.giftech.terbit.domain.model.Article
import com.giftech.terbit.domain.repository.IArticleRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(
    private val articleLocalDataSource: ArticleLocalDataSource,
    private val mapper: ArticleMapper,
) : IArticleRepository {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getArticleByWeekDay(week: Int, day: Int): Flow<Article?> {
        return articleLocalDataSource.getAll()
            .mapLatest { articleList ->
                articleList.firstOrNull { it.week == week && it.day == day }
                    .let {
                        if (it != null) mapper.mapToDomain(it) else null
                    }
            }
    }
    
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getArticlesByWeek(week: Int): Flow<List<Article>> {
        return articleLocalDataSource.getAll()
            .mapLatest { articleList ->
                articleList.filter { it.week == week }
                    .let {
                        mapper.mapToDomain(it)
                    }
            }
    }
    
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getArticleById(id: Int): Flow<Article?> {
        return articleLocalDataSource.getAll()
            .mapLatest { articleList ->
                articleList.firstOrNull { it.articleId == id }
                    .let {
                        if (it != null) mapper.mapToDomain(it) else null
                    }
            }
    }
    
}