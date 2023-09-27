package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.model.Article
import com.giftech.terbit.domain.repository.IArticleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticleUsecase @Inject constructor(
    private val articleRepository: IArticleRepository,
) {
    
    fun getArticleByWeekDay(week: Int, day: Int): Flow<Article?> {
        return articleRepository.getArticleByWeekDay(week, day)
    }
    
    fun getArticlesByWeek(week: Int): Flow<List<Article>> {
        return articleRepository.getArticlesByWeek(week)
    }
    
    fun getArticleById(id: Int): Flow<Article?> {
        return articleRepository.getArticleById(id)
    }
    
}