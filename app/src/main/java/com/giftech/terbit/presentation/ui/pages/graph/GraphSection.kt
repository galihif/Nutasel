package com.giftech.terbit.presentation.ui.pages.graph

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.giftech.terbit.domain.model.FfqFoodCategory
import com.giftech.terbit.presentation.ui.components.molecules.ChartLegend
import com.giftech.terbit.presentation.ui.components.molecules.MenuButton
import com.giftech.terbit.presentation.ui.theme.dark_CustomColor2
import com.giftech.terbit.presentation.ui.theme.light_CustomColor2Container
import com.giftech.terbit.presentation.ui.theme.light_onCustomColor2Container
import com.patrykandpatrick.vico.core.entry.ChartEntry

@Composable
fun WeeklyProgramProgress(
    weeklyProgramProgress: Int,
    enableAnimation: Boolean = true,
) {
    CircularProgressBar(
        value = weeklyProgramProgress,
        enableAnimation = enableAnimation,
        modifier = Modifier
            .fillMaxWidth(),
    )
    
    Spacer(modifier = Modifier.height(16.dp))
    
    Text(
        text = "Keterangan",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurface,
    )
    Spacer(modifier = Modifier.height(8.dp))
    ChartLegend(
        text = "Aktivitas dikerjakan",
        color = MaterialTheme.colorScheme.primary,
    )
}

@Composable
fun PreTestAsaq(
    preTestAsaqChartEntry: List<ChartEntry>,
    preTestAsaqChartXLabels: List<String>,
    preTestAsaqChartMaxY: Int,
    preTestAsaqChartYLabelCount: Int,
    enableAnimation: Boolean = true,
    chartHeightDp: Int = 220,
) {
    Text(
        text = "Sedenter Awal",
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface,
    )
    
    if (preTestAsaqChartEntry.isNotEmpty()) {
        ColumnChart(
            entries = listOf(preTestAsaqChartEntry),
            xLabels = preTestAsaqChartXLabels,
            maxY = preTestAsaqChartMaxY,
            yLabelCount = preTestAsaqChartYLabelCount,
            yTitle = "Jam",
            labelFormatter = AsaqLabelFormatter(),
            xValueFormatter = AsaqChartXValueFormatter(preTestAsaqChartXLabels),
            enableAnimation = enableAnimation,
            modifier = Modifier
                .height(chartHeightDp.dp),
        )
    } else {
        Text(
            text = "Belum ada data",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier
                .padding(top = 16.dp),
        )
    }
    
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun PostTestAsaq(
    preTestAsaqChartEntry: List<ChartEntry>,
    postTestAsaqChartEntry: List<ChartEntry>,
    postTestAsaqChartXLabels: List<String>,
    postTestAsaqChartMaxY: Int,
    postTestAsaqChartYLabelCount: Int,
    enableAnimation: Boolean = true,
    chartHeightDp: Int = 220,
) {
    Text(
        text = "Sedenter Akhir",
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface,
    )
    
    if (postTestAsaqChartEntry.isNotEmpty()) {
        ColumnChart(
            entries = listOf(postTestAsaqChartEntry),
            xLabels = postTestAsaqChartXLabels,
            maxY = postTestAsaqChartMaxY,
            yLabelCount = postTestAsaqChartYLabelCount,
            yTitle = "Jam",
            labelFormatter = AsaqLabelFormatter(),
            xValueFormatter = AsaqChartXValueFormatter(postTestAsaqChartXLabels),
            enableAnimation = enableAnimation,
            modifier = Modifier
                .height(chartHeightDp.dp),
        )
    } else {
        Text(
            text = "Belum ada data",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier
                .padding(top = 16.dp),
        )
    }
    
    if (preTestAsaqChartEntry.isNotEmpty() || postTestAsaqChartEntry.isNotEmpty()) {
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Keterangan",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            ChartLegend(
                text = "",
                color = MaterialTheme.colorScheme.primary
            )
            ChartLegend(
                text = "Tingkat aktivitas sedenter",
                color = dark_CustomColor2,
                textColor = MaterialTheme.colorScheme.primary,
            )
        }
        ChartLegend(
            text = "Qn = Pertanyaan ke-n",
        )
    }
}

@Composable
fun WeeklyAsaq(
    weeklyAsaqSelectedWeek: Int,
    weeklyAsaqSelectedDayOfWeek: Int,
    weeklyAsaqResponseChartEntry: List<ChartEntry>,
    weeklyAsaqResponseChartXLabels: List<String>,
    weeklyAsaqResponseChartMaxY: Int,
    weeklyAsaqResponseChartYLabelCount: Int,
    onSelectDayOfWeek: () -> Unit,
    onSelectWeek: () -> Unit,
    enableAnimation: Boolean = true,
    chartHeightDp: Int = 220,
) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        MenuButton(
            text = "Hari $weeklyAsaqSelectedDayOfWeek",
            onClick = {
                onSelectDayOfWeek()
            },
        )
        MenuButton(
            text = "Minggu $weeklyAsaqSelectedWeek",
            onClick = {
                onSelectWeek()
            },
        )
    }
    
    if (weeklyAsaqResponseChartEntry.isNotEmpty()) {
        ColumnChart(
            entries = listOf(weeklyAsaqResponseChartEntry),
            xLabels = weeklyAsaqResponseChartXLabels,
            maxY = weeklyAsaqResponseChartMaxY,
            yLabelCount = weeklyAsaqResponseChartYLabelCount,
            yTitle = "Jam",
            labelFormatter = AsaqLabelFormatter(),
            xValueFormatter = AsaqChartXValueFormatter(weeklyAsaqResponseChartXLabels),
            enableAnimation = enableAnimation,
            modifier = Modifier
                .height(chartHeightDp.dp),
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Keterangan",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.height(8.dp))
        ChartLegend(
            text = "Tingkat aktivitas sedenter",
            color = MaterialTheme.colorScheme.primary,
        )
        ChartLegend(
            text = "Qn = Pertanyaan ke-n",
        )
    } else {
        Text(
            text = "Belum ada data",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outline,
        )
    }
}

@Composable
fun FfqScore(
    preTestFfqScore: Int,
    postTestFfqScore: Int,
    ffqScoreChartEntries: List<List<ChartEntry>>,
    ffqScoreChartXLabels: List<String>,
    ffqScoreChartMaxY: Int,
    ffqScoreChartYLabelCount: Int,
    enableAnimation: Boolean = true,
    chartHeightDp: Int = 320,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = "Skor FFQ Total",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .weight(1f),
        )
        ScoreText(
            value = preTestFfqScore,
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
        )
        ScoreText(
            value = postTestFfqScore,
            backgroundColor = light_CustomColor2Container,
            textColor = light_onCustomColor2Container,
        )
    }
    
    Spacer(modifier = Modifier.height(8.dp))
    
    if (ffqScoreChartEntries.isNotEmpty()) {
        ColumnChart(
            entries = ffqScoreChartEntries,
            xLabels = ffqScoreChartXLabels,
            maxY = ffqScoreChartMaxY,
            yLabelCount = ffqScoreChartYLabelCount,
            labelFormatter = FfqScoreLabelFormatter(ffqScoreChartXLabels),
            enableAnimation = enableAnimation,
            modifier = Modifier
                .height(chartHeightDp.dp),
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Keterangan",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            ChartLegend(
                text = "Skor FFQ awal",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .weight(1f),
            )
            ChartLegend(
                text = "Skor FFQ akhir",
                color = dark_CustomColor2,
                modifier = Modifier
                    .weight(1f),
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            Column(
                modifier = Modifier
                    .weight(1f),
            ) {
                ChartLegend(
                    text = "MP = Makanan Pokok",
                )
                ChartLegend(
                    text = "LN = Lauk Nabati",
                )
                ChartLegend(
                    text = "BU = Buah-buahan",
                )
                ChartLegend(
                    text = "SE = Selingan",
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f),
            ) {
                ChartLegend(
                    text = "LH = Lauk Hewani",
                )
                ChartLegend(
                    text = "SY = Sayuran",
                )
                ChartLegend(
                    text = "MI = Minuman",
                )
            }
        }
    } else {
        Text(
            text = "Belum ada data",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier
                .padding(top = 16.dp),
        )
    }
}

@Composable
private fun ScoreText(
    value: Int,
    backgroundColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier,
) {
    Text(
        text = value.toString(),
        style = MaterialTheme.typography.labelLarge,
        color = textColor,
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(100),
            )
            .padding(
                horizontal = 24.dp,
                vertical = 6.dp,
            ),
    )
}

@Composable
fun FfqCategory(
    ffqCategoryOptionsCategory: List<FfqFoodCategory>,
    ffqCategorySelectedCategory: Int,
    ffqCategoryChartEntry: List<ChartEntry>,
    ffqCategoryChartXLabels: List<String>,
    ffqCategoryChartYLabels: List<String>,
    onSelectCategory: () -> Unit,
    enableAnimation: Boolean = true,
    chartHeightDp: Int = 260,
) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        MenuButton(
            text = ffqCategoryOptionsCategory
                .firstOrNull { it.foodCategoryId == ffqCategorySelectedCategory }?.name.orEmpty(),
            onClick = {
                onSelectCategory()
            },
        )
    }
    
    if (ffqCategoryChartEntry.isNotEmpty()) {
        LineChart(
            entries = listOf(ffqCategoryChartEntry),
            xLabels = ffqCategoryChartXLabels,
            yLabels = ffqCategoryChartYLabels,
            labelFormatter = FfqCategoryLabelFormatter(ffqCategoryChartXLabels),
            enableAnimation = enableAnimation,
            modifier = Modifier
                .height(chartHeightDp.dp),
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Keterangan",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.height(8.dp))
        ChartLegend(
            text = "Tingkat konsumsi",
            color = MaterialTheme.colorScheme.primary,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            Column(
                modifier = Modifier
                    .weight(1f),
            ) {
                ChartLegend(
                    text = "H = Hari",
                )
                ChartLegend(
                    text = "B = Bulan",
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f),
            ) {
                ChartLegend(
                    text = "M = Minggu",
                )
            }
        }
    } else {
        Text(
            text = "Belum ada data",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outline,
        )
    }
}