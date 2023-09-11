package com.giftech.terbit.ui.pages.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.giftech.terbit.ui.theme.light_CustomColor2
import com.giftech.terbit.ui.theme.light_onCustomColor2
import com.giftech.terbit.ui.theme.md_theme_light_tertiary
import com.giftech.terbit.ui.utils.annotatedStringResource

@ExperimentalMaterial3Api
@Composable
fun ArticleScreen(
    week: Int,
    day: Int,
    viewModel: ArticleViewModel = hiltViewModel()
) {
    LaunchedEffect(week, day) {
        viewModel.getArticleByWeekDay(week, day)
    }
    val article by remember {
        viewModel.article
    }
    if (article != null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Artikel")
                    },
                    navigationIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                        }
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(it)
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Card(
                    shape = RoundedCornerShape(6.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Surface() {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                        ) {
                            Text(
                                text = annotatedStringResource(id = article!!.title),
                                modifier = Modifier.padding(12.dp),
                                style = MaterialTheme.typography.titleMedium,
                            )
                            Image(
                                painter = painterResource(id = article!!.imageRes),
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                text = "${article!!.readDuration} menit membaca",
                                modifier = Modifier.padding(12.dp),
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }
                Row {
                    article!!.category.forEach {
                        Text(
                            text = it,
                            modifier = Modifier
                                .clip(
                                    RoundedCornerShape(64.dp)
                                )
                                .background(
                                    if (day == 3) light_CustomColor2 else md_theme_light_tertiary
                                )
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            color = light_onCustomColor2,
                            style = MaterialTheme.typography.labelSmall
                        )
                        Spacer(Modifier.width(6.dp))
                    }
                }
                Text(
                    text = annotatedStringResource(id = article!!.content, "Galih"),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Justify
                )
                Column {
                    Text(
                        text = "Referensi :",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Justify
                    )
                    Text(
                        text = article!!.reference,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Justify
                    )
                }

                if (article!!.imageSource.isNotEmpty()) {
                    Column {
                        Text(
                            text = "Sumber Gambar :",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Justify
                        )
                        Text(
                            text = article!!.imageSource,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Justify
                        )
                    }
                }
            }
        }
    }
}
