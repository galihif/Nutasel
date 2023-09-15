package com.giftech.terbit.ui.pages.monitoringdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.rounded.CheckBox
import androidx.compose.material.icons.rounded.CheckBoxOutlineBlank
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.giftech.terbit.R
import com.giftech.terbit.domain.model.FillOutAsaq
import com.giftech.terbit.domain.model.FillOutFfq
import com.giftech.terbit.domain.model.Program
import com.giftech.terbit.domain.model.ReadArticle
import com.giftech.terbit.ui.components.molecules.AppBar
import com.giftech.terbit.ui.route.Screen

@Composable
fun MonitoringDetailsScreen(
    week: Int,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: MonitoringDetailsViewModel = hiltViewModel(),
) {
    LaunchedEffect(week) {
        viewModel.onEvent(
            MonitoringDetailsEvent.Init(
                week = week,
            )
        )
    }
    val state = viewModel.state.value
    
    MonitoringDetailsContent(
        state = state,
        navController = navController,
        modifier = modifier,
    )
    
    LaunchedEffect(state.needLaunchFinishScreen) {
        if (state.needLaunchFinishScreen) {
            // TODO: Change to finish screen
            navController.navigate(
                Screen.Profesional.route
            )
        }
    }
}

@Composable
private fun MonitoringDetailsContent(
    state: MonitoringDetailsState,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            AppBar(
                title = "Program",
                onBack = navController::popBackStack,
            )
        },
        modifier = modifier,
    ) { contentPadding ->
        LazyColumn(
            contentPadding = PaddingValues(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding),
        ) {
            item {
                Column {
                    Text(
                        text = "Minggu ${state.week}",
                        style = MaterialTheme.typography.headlineLarge,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Yuk, selesaikan program mingguan kamu.",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Program Kamu",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
            
            items(
                items = state.programList,
                key = { it.programId },
            ) { program ->
                ProgramItem(
                    program = program,
                    isAvailable = program.isCompleted ||
                            (if (state.currentWeek == program.week) {
                                state.currentDayOfWeek >= program.dayOfWeek!!
                            } else {
                                state.currentWeek > program.week!!
                            } && state.programList
                                .filter { it.dayOfWeek!! == program.dayOfWeek!! - 1 }
                                .all { it.isCompleted }),
                    onClick = {
                        navController.navigate(
                            when (program) {
                                is FillOutAsaq -> Screen.WeeklyAsaq.createRoute(
                                    programId = program.programId,
                                )
                                
                                is FillOutFfq -> Screen.FfqMain.createRoute(
                                    programId = program.programId,
                                )
                                
                                is ReadArticle -> Screen.Article.createRoute(
                                    week = program.week!!,
                                    day = program.dayOfWeek!!,
                                )
                            }
                        )
                    })
            }
        }
    }
}

@Composable
private fun ProgramItem(
    program: Program,
    isAvailable: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .clickable(enabled = isAvailable) {
                onClick()
            }
            .padding(vertical = 8.dp, horizontal = 16.dp),
    ) {
        Image(
            painter = painterResource(
                when (program) {
                    is FillOutAsaq -> R.drawable.img_fill_out_asaq_icon_224
                    is ReadArticle -> R.drawable.img_read_article_icon_224
                    else -> R.drawable.vector_onboard_asaq2
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .size(56.dp),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            Text(
                text = when (program) {
                    is FillOutAsaq -> "Pantau Sedenter"
                    is ReadArticle -> "Baca Artikel"
                    else -> "-"
                },
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = "Hari ${program.dayOfWeek}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            imageVector = if (program.isCompleted) {
                Icons.Rounded.CheckBox
            } else {
                if (isAvailable) {
                    Icons.Rounded.CheckBoxOutlineBlank
                } else {
                    Icons.Outlined.Lock
                }
            },
            contentDescription = "Belum tersedia",
        )
    }
}