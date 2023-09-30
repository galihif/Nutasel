package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.model.Article
import com.giftech.terbit.domain.repository.IArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ArticleUsecase @Inject constructor(
    private val articleRepository: IArticleRepository,
) {
    
    suspend fun getArticleByWeekDay(week: Int, day: Int): Flow<Article?> {
        return articleRepository.getArticleByWeekDay(week, day)
            .flowOn(Dispatchers.IO)
    }
    
    suspend fun getArticlesByWeek(week: Int): Flow<List<Article>> {
        return articleRepository.getArticlesByWeek(week)
            .flowOn(Dispatchers.IO)
    }
    
    suspend fun getArticleById(id: Int): Flow<Article?> {
        return articleRepository.getArticleById(id)
            .flowOn(Dispatchers.IO)
    }
    
}