package com.giftech.terbit.ui.pages.article

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.giftech.terbit.domain.model.Article
import com.giftech.terbit.domain.usecase.ArticleUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel 
@Inject constructor(
    private val articleUseCase: ArticleUsecase
) : ViewModel() {

    val _article =  mutableStateOf<Article?>(null)
    val article: State<Article?> = _article

    fun getArticleByWeekDay(week: Int, day: Int) {
        _article.value = articleUseCase.getArticleByWeekDay(week, day)
    }
    
}