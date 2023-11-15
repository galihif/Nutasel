package com.giftech.terbit.presentation.ui.pages.article

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.giftech.terbit.presentation.ui.route.Screen
import com.giftech.terbit.presentation.ui.theme.light_CustomColor2
import com.giftech.terbit.presentation.ui.theme.light_CustomColor3Container
import com.giftech.terbit.presentation.ui.theme.light_onCustomColor2
import com.giftech.terbit.presentation.ui.theme.light_onCustomColor3Container
import com.giftech.terbit.presentation.ui.theme.md_theme_light_tertiary
import com.giftech.terbit.presentation.util.annotatedStringResource
import kotlinx.coroutines.delay

@ExperimentalMaterial3Api
@Composable
fun ArticleScreen(
    programId: Int,
    week: Int,
    day: Int,
    navController: NavController,
    viewModel: ArticleViewModel = hiltViewModel(),
) {
    LaunchedEffect(week, day) {
        viewModel.getArticleByWeekDay(week, day)
        viewModel.getUserName()
    }
    val userName by remember {
        viewModel.userName
    }.collectAsState()
    val article by remember {
        viewModel.article
    }.collectAsState()
    var timeLeftSeconds by remember {
        mutableIntStateOf(article?.readDuration ?: 0)
    }
    var minuteLeft by remember {
        mutableStateOf("0")
    }
    var secondLeft by remember {
        mutableStateOf("00")
    }
    var timerOn by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(article) {
        if (article != null) {
            timeLeftSeconds = article!!.readDuration.times(60)
        }
    }
    LaunchedEffect(timeLeftSeconds) {
        if (timeLeftSeconds > 0) {
            timerOn = true
            delay(1000)
            timeLeftSeconds--

            minuteLeft = (timeLeftSeconds / 60).toString() // Convert to string
            secondLeft = (timeLeftSeconds % 60).toString() // Convert to string

            // Add leading zeros if necessary
            if (minuteLeft.length == 1) {
                minuteLeft = "0$minuteLeft"
            }

            if (secondLeft.length == 1) {
                secondLeft = "0$secondLeft"
            }
        }
    }
    var openDialog by remember {
        mutableStateOf(false)
    }
    val onBack = {
        viewModel.complete(programId)
        navController.popBackStack()
        navController.navigate(
            Screen.ArticleComplete.createRoute(
                week = week,
                day = day,
            )
        )
    }
    val backHandling = {
        if (timeLeftSeconds <= 0 && timerOn) {
            onBack()
        }else{
            openDialog = true
        }
    }
    BackHandler {
        backHandling()
    }
    if (openDialog){
        AlertDialog(
            onDismissRequest = {
                openDialog = false
            },
            title = {
                Text(text = "Peringatan")
            },
            text = {
                Text(text = "Anda baru bisa kembali setelah countdown selesai")
            },
            confirmButton = {
                Button(
                    onClick = {
                        openDialog = false
                    }
                ) {
                    Text(text = "Oke")
                }
            },
        )
    }

    if (article != null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Artikel")
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                backHandling()
                            }
                        ) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                        }
                    },
                    actions = {
                        Box(
                            modifier = Modifier.padding(horizontal = 14.dp).background(
                                color = light_CustomColor3Container,
                                shape = RoundedCornerShape(30.dp)
                            )
                        ) {
                            Text(
                                text = "$minuteLeft : $secondLeft",
                                modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp),
                                color = light_onCustomColor3Container,
                                style = MaterialTheme.typography.titleSmall
                            )
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
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                    ),
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.surface,
                    ) {
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
                                    .fillMaxWidth(),
                            )
                            Text(
                                text = "Yuk luangkan  waktu ${article!!.readDuration} menit membaca!",
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
                    text = annotatedStringResource(id = article!!.content, userName),
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
            }
        }
    }
}