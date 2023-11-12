package com.giftech.terbit.presentation.ui.pages.weeklysummary

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.giftech.terbit.R
import com.giftech.terbit.domain.enums.SedenterType
import com.giftech.terbit.presentation.ui.components.atoms.PrimaryButton
import com.giftech.terbit.presentation.ui.components.molecules.HeroColumn
import com.giftech.terbit.presentation.ui.theme.CustomColor1
import com.giftech.terbit.presentation.ui.theme.CustomColor2
import com.giftech.terbit.presentation.ui.theme.light_CustomColor1
import com.giftech.terbit.presentation.ui.theme.light_CustomColor2
import com.giftech.terbit.presentation.ui.theme.light_CustomColor3
import com.giftech.terbit.presentation.ui.theme.light_onCustomColor1
import com.giftech.terbit.presentation.ui.theme.light_onCustomColor2
import com.giftech.terbit.presentation.ui.theme.light_onCustomColor3

@Composable
fun WeeklySummaryScreen(
    week: Int,
    onNext: () -> Unit,
    viewModel: WeeklySummaryViewModel = hiltViewModel(),
) {
    viewModel.onEvent(
        WeeklySummaryEvent.Init(
            week = week,
        )
    )
    
    val state = viewModel.state.value
    
    val onNextClick = {
        viewModel.onEvent(
            WeeklySummaryEvent.UpdatePresentedStatus(
                week = state.week,
            )
        )
        onNext()
    }
    
    WeeklySummaryContent(
        state = state,
        onNext = onNextClick,
    )
    
    BackHandler {
        onNextClick()
    }
}

@Composable
private fun WeeklySummaryContent(
    state: WeeklySummaryState,
    onNext: () -> Unit,
) {
    val bgColor = when (state.sedentaryLevel) {
        SedenterType.RINGAN -> light_CustomColor2
        SedenterType.SEDANG -> light_CustomColor1
        SedenterType.BERAT -> light_CustomColor3
    }
    val accentColor = when (state.sedentaryLevel) {
        SedenterType.RINGAN -> light_onCustomColor2
        SedenterType.SEDANG -> light_onCustomColor1
        SedenterType.BERAT -> light_onCustomColor3
    }
    
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(bgColor),
    ) {
        HeroColumn(
            title = "Hasil Pemantauan Mingguan",
            description = "",
            imageRes = R.drawable.img_weekly_summary_1080,
            titleColor = accentColor,
            descColor = accentColor,
            imageHeight = 280,
        )
        Column(
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd = 12.dp,
                    ),
                )
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd = 12.dp,
                    ),
                ),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier
                    .padding(24.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    ContainerTop(
                        title = "Rata-rata",
                        value = "${state.sedentaryAverageHours} Jam",
                        icon = R.drawable.ic_avg_time_100,
                        iconColor = CustomColor2,
                        modifier = Modifier
                            .weight(1f),
                    )
                    ContainerTop(
                        title = "Sedenter",
                        value = state.sedentaryLevel.title,
                        icon = R.drawable.ic_sedentary_level_100,
                        iconColor = CustomColor1,
                        modifier = Modifier
                            .weight(1f),
                    )
                }
                ContainerBottom(
                    content = state.sedentaryLevel.recommendation,
                )
                PrimaryButton(
                    text = "Selanjutnya",
                    onClick = onNext,
                    modifier = Modifier
                        .fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
private fun ContainerTop(
    title: String,
    value: String,
    @DrawableRes icon: Int,
    iconColor: Color,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colorScheme.outline,
            )
            .padding(12.dp),
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier
                .size(100.dp),
        )
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun ContainerBottom(
    content: String,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colorScheme.outline,
            )
            .padding(12.dp),
    ) {
        Text(
            text = content,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
        )
    }
}