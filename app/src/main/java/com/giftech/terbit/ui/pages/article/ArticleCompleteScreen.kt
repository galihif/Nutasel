package com.giftech.terbit.ui.pages.article

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.giftech.terbit.R
import com.giftech.terbit.ui.components.atoms.PrimaryButton
import com.giftech.terbit.ui.components.molecules.HeroColumn
import com.giftech.terbit.ui.theme.light_CustomColor1Container
import com.giftech.terbit.ui.theme.light_onCustomColor1Container

@ExperimentalMaterial3Api
@Composable
fun ArticleCompleteScreen(
    week: Int,
    day: Int,
    onNext: () -> Unit = {},
    viewModel: ArticleViewModel = hiltViewModel()
) {
    LaunchedEffect(week, day) {
        viewModel.getArticleByWeekDay(week, day)
    }
    val article by remember {
        viewModel.article
    }
    if (article!=null){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HeroColumn(
                modifier = Modifier.padding(vertical = 16.dp),
                title = article!!.completeTitle,
                description = article!!.completeBody,
                imageRes = R.drawable.vector_article_complete,
                imageHeight = 200
            )
            Surface(
                color = Color.Transparent,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = article!!.title),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier
                            .background(
                                MaterialTheme.colorScheme.surface,
                                RoundedCornerShape(6.dp)
                            )
                            .padding(6.dp)
                            .weight(1f),
                        textAlign = TextAlign.Center,
                    )
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Selesai",
                            modifier = Modifier
                                .wrapContentWidth()
                                .clip(
                                    RoundedCornerShape(64.dp)
                                )
                                .background(
                                    light_CustomColor1Container
                                )
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                                .padding(6.dp),
                            color = light_onCustomColor1Container,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
            PrimaryButton(
                text = "Selanjutnya",
                onClick = onNext,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}