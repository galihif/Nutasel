package com.giftech.terbit.domain.repository

import com.giftech.terbit.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface IArticleRepository {
    
    fun getArticleByWeekDay(week: Int, day: Int): Flow<Article?>
    
    fun getArticlesByWeek(week: Int): Flow<List<Article>>
    
    fun getArticleById(id: Int): Flow<Article?>
    
}