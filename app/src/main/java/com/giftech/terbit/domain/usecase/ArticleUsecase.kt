package com.giftech.terbit.domain.usecase

import com.giftech.terbit.domain.repository.IArticleRepository
import javax.inject.Inject

class ArticleUsecase @Inject constructor(
    private val articleRepository: IArticleRepository
) {

    fun getArticleByWeekDay(week:Int, day:Int) = articleRepository.getArticleByWeekDay(week, day)
    fun getArticlesByWeek(week:Int) = articleRepository.getArticlesByWeek(week)
    fun getArticleById(id:Int) = articleRepository.getArticleById(id)
}