package com.giftech.terbit.domain.repository

import com.giftech.terbit.domain.model.Article

interface IArticleRepository {

    fun getArticleByWeekDay(week:Int, day:Int):Article?

    fun getArticlesByWeek(week:Int):List<Article>

    fun getArticleById(id:Int):Article?
}