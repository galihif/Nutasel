package com.giftech.terbit.domain.repository

import com.giftech.terbit.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface IArticleRepository {
    
    suspend fun getArticleByWeekDay(week: Int, day: Int): Flow<Article?>
    
    suspend fun getArticlesByWeek(week: Int): Flow<List<Article>>
    
    suspend fun getArticleById(id: Int): Flow<Article?>
    
}