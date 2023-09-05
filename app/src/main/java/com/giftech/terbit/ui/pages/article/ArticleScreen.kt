package com.giftech.terbit.ui.pages.article

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ArticleScreen(
    viewModel: ArticleViewModel = hiltViewModel()
) {
    LaunchedEffect(true){
        viewModel.getArticleByWeekDay(2, 7)
    }
    val article by remember {
        viewModel.article
    }
    if (article != null){
        Column {
            Text(text = article!!.title)
            Text(text = stringResource(id = article!!.content, "Coy"))
        }
    }

}