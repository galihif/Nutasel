package com.giftech.terbit.ui.pages.article

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftech.terbit.domain.model.Article
import com.giftech.terbit.domain.usecase.ArticleUsecase
import com.giftech.terbit.domain.usecase.CompleteProgramUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel
@Inject constructor(
    private val articleUseCase: ArticleUsecase,
    private val completeProgramUseCase: CompleteProgramUseCase,
) : ViewModel() {
    
    private val _article = mutableStateOf<Article?>(null)
    val article: State<Article?> = _article
    
    fun getArticleByWeekDay(week: Int, day: Int) {
        viewModelScope.launch {
            articleUseCase.getArticleByWeekDay(week, day).collect { article ->
                _article.value = article
            }
        }
    }
    
    fun complete(programId: Int) {
        viewModelScope.launch {
            completeProgramUseCase(
                programId = programId,
            )
        }
    }
    
}