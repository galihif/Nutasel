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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.giftech.terbit.R
import com.giftech.terbit.domain.model.FillOutAsaq
import com.giftech.terbit.domain.model.Program
import com.giftech.terbit.domain.model.ReadArticle
import com.giftech.terbit.ui.components.atoms.PrimaryButton
import com.giftech.terbit.ui.components.molecules.AppBar
import com.giftech.terbit.ui.route.Screen

// TODO: Logic menyusul
@Composable
fun MonitoringDetailsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    MonitoringDetailsContent(
        navController = navController,
        modifier = modifier,
    )
}

@Composable
private fun MonitoringDetailsContent(
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
                        text = "Minggu Kedua",
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
            
            
            val dummyProgramList = listOf(
                FillOutAsaq(
                    programId = 3,
                    week = 1,
                    dayOfWeek = 1,
                    isComplete = true,
                ),
                FillOutAsaq(
                    programId = 4,
                    week = 1,
                    dayOfWeek = 2,
                    isComplete = false,
                ),
                FillOutAsaq(
                    programId = 5,
                    week = 1,
                    dayOfWeek = 3,
                    isComplete = false,
                ),
                ReadArticle(
                    programId = 10,
                    week = 1,
                    dayOfWeek = 3,
                    isComplete = false,
                ),
                FillOutAsaq(
                    programId = 6,
                    week = 1,
                    dayOfWeek = 4,
                    isComplete = false,
                ),
                FillOutAsaq(
                    programId = 7,
                    week = 1,
                    dayOfWeek = 5,
                    isComplete = false,
                ),
                FillOutAsaq(
                    programId = 8,
                    week = 1,
                    dayOfWeek = 6,
                    isComplete = false,
                ),
                FillOutAsaq(
                    programId = 9,
                    week = 1,
                    dayOfWeek = 7,
                    isComplete = false,
                ),
                ReadArticle(
                    programId = 11,
                    week = 1,
                    dayOfWeek = 7,
                    isComplete = false,
                ),
            )
            
            items(
                items = dummyProgramList,
                key = { it.programId },
            ) { item ->
                ProgramItem(
                    program = item,
                    isPrevProgramComplete = dummyProgramList
                        .firstOrNull { it.programId == item.programId - 1 }
                        ?.isComplete,
                    onClick = {
                        navController.navigate(
                            Screen.ASAQ.route
                        )
                    })
            }
            
            item {
                Spacer(modifier = Modifier.height(24.dp))
                
                PrimaryButton(
                    text = "Selesai",
                    onClick = {
                        navController.apply {
                            navController.popBackStack()
                        }
                    },
                    enabled = dummyProgramList.all { it.isComplete },
                    modifier = Modifier
                        .fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
private fun ProgramItem(
    program: Program,
    isPrevProgramComplete: Boolean?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .clickable(enabled = program.isComplete || isPrevProgramComplete == true) {
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
            imageVector = if (program.isComplete) {
                Icons.Rounded.CheckBox
            } else {
                if (isPrevProgramComplete == true) {
                    Icons.Rounded.CheckBoxOutlineBlank
                } else {
                    Icons.Outlined.Lock
                }
            },
            contentDescription = "Belum tersedia",
        )
    }
}