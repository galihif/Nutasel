package com.giftech.terbit.ui.pages.graph

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.giftech.terbit.ui.components.molecules.ChartLegend
import com.giftech.terbit.ui.components.molecules.MenuButton
import com.giftech.terbit.ui.theme.dark_CustomColor2
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.list.ListDialog
import com.maxkeppeler.sheets.list.models.ListOption
import com.maxkeppeler.sheets.list.models.ListSelection

@Composable
fun GraphScreen(
    modifier: Modifier = Modifier,
    viewModel: GraphViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    
    GraphContent(
        state = state,
        viewModel = viewModel,
        modifier = modifier,
    )
}

@Composable
private fun GraphContent(
    state: GraphState,
    viewModel: GraphViewModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
    ) {
        WeeklyProgramProgressSection(
            state = state,
        )
        Spacer(modifier = Modifier.height(40.dp))
        PrePostTestAsaqSection(
            state = state,
        )
        Spacer(modifier = Modifier.height(40.dp))
        DailyAsaqSection(
            state = state,
            viewModel = viewModel,
        )
        Spacer(modifier = Modifier.height(40.dp))
        FFQScoreSection(
            state = state,
        )
        Spacer(modifier = Modifier.height(40.dp))
        FFQCategorySection(
            state = state,
            viewModel = viewModel,
        )
    }
    
    ChartOptionsDialog(
        state = state,
        viewModel = viewModel,
    )
}

@Composable
private fun WeeklyProgramProgressSection(
    state: GraphState,
) {
    Text(
        text = "Progres",
        style = MaterialTheme.typography.titleMedium,
    )
    Spacer(modifier = Modifier.height(16.dp))
    CircularProgressBar(
        value = state.weeklyProgramProgress,
        modifier = Modifier
            .fillMaxWidth()
    )
    
    Spacer(modifier = Modifier.height(16.dp))
    
    Text(
        text = "Keterangan",
        style = MaterialTheme.typography.bodySmall,
    )
    Spacer(modifier = Modifier.height(8.dp))
    ChartLegend(
        text = "Aktivitas dikerjakan",
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
private fun PrePostTestAsaqSection(
    state: GraphState,
) {
    Text(
        text = "Sedenter Awal",
        style = MaterialTheme.typography.titleMedium,
    )
    
    if (state.preTestAsaqChartEntry.isNotEmpty()) {
        ColumnChart(
            entries = listOf(state.preTestAsaqChartEntry),
            xLabels = state.preTestAsaqChartXLabels,
            maxY = state.preTestAsaqChartMaxY,
            yLabelCount = state.preTestAsaqChartYLabelCount,
            yTitle = "Jam",
            labelFormatter = AsaqLabelFormatter(),
            xValueFormatter = AsaqChartXValueFormatter(state.preTestAsaqChartXLabels),
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
    
    Text(
        text = "Sedenter Akhir",
        style = MaterialTheme.typography.titleMedium,
    )
    
    if (state.postTestAsaqChartEntry.isNotEmpty()) {
        ColumnChart(
            entries = listOf(state.postTestAsaqChartEntry),
            xLabels = state.postTestAsaqChartXLabels,
            maxY = state.postTestAsaqChartMaxY,
            yLabelCount = state.postTestAsaqChartYLabelCount,
            yTitle = "Jam",
            labelFormatter = AsaqLabelFormatter(),
            xValueFormatter = AsaqChartXValueFormatter(state.postTestAsaqChartXLabels),
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
    
    if (state.preTestAsaqChartEntry.isNotEmpty() || state.postTestAsaqChartEntry.isNotEmpty()) {
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Keterangan",
            style = MaterialTheme.typography.bodySmall,
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
private fun DailyAsaqSection(
    state: GraphState,
    viewModel: GraphViewModel,
) {
    Text(
        text = "Sedenter Harian",
        style = MaterialTheme.typography.titleMedium,
    )
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        MenuButton(
            text = "Hari ${state.weeklyAsaqSelectedDayOfWeek}",
            onClick = {
                viewModel.onEvent(
                    GraphEvent.ShowWeeklyAsaqOptionsDayOfWeekDialog
                )
            },
        )
        MenuButton(
            text = "Minggu ${state.weeklyAsaqSelectedWeek}",
            onClick = {
                viewModel.onEvent(
                    GraphEvent.ShowWeeklyAsaqOptionsWeekDialog
                )
            },
        )
    }
    
    if (state.weeklyAsaqResponseChartEntry.isNotEmpty()) {
        ColumnChart(
            entries = listOf(state.weeklyAsaqResponseChartEntry),
            xLabels = state.weeklyAsaqResponseChartXLabels,
            maxY = state.weeklyAsaqResponseChartMaxY,
            yLabelCount = state.weeklyAsaqResponseChartYLabelCount,
            yTitle = "Jam",
            labelFormatter = AsaqLabelFormatter(),
            xValueFormatter = AsaqChartXValueFormatter(state.weeklyAsaqResponseChartXLabels),
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Keterangan",
            style = MaterialTheme.typography.bodySmall,
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
private fun FFQScoreSection(
    state: GraphState,
) {
    Text(
        text = "Frekuensi Makanan",
        style = MaterialTheme.typography.titleMedium,
    )
    
    if (state.postTestFfqScore >= 0) {
        Spacer(modifier = Modifier.height(24.dp))
        
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Skor FFQ Akhir Total",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .weight(1f),
            )
            Text(
                text = state.postTestFfqScore.toString(),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(100),
                    )
                    .padding(
                        horizontal = 24.dp,
                        vertical = 6.dp,
                    ),
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
    }
    
    if (state.ffqScoreChartEntries.isNotEmpty()) {
        ColumnChart(
            entries = state.ffqScoreChartEntries,
            xLabels = state.ffqScoreChartXLabels,
            maxY = state.ffqScoreChartMaxY,
            yLabelCount = state.ffqScoreChartYLabelCount,
            labelFormatter = FfqScoreLabelFormatter(state.ffqScoreChartXLabels),
            modifier = Modifier
                .height(320.dp),
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Keterangan",
            style = MaterialTheme.typography.bodySmall,
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
private fun FFQCategorySection(
    state: GraphState,
    viewModel: GraphViewModel,
) {
    Text(
        text = "Frekuensi Kategori Makanan",
        style = MaterialTheme.typography.titleMedium,
    )
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        MenuButton(
            text = state.ffqCategoryOptionsCategory.first { it.foodCategoryId == state.ffqCategorySelectedCategory }.name,
            onClick = {
                viewModel.onEvent(
                    GraphEvent.ShowFfqCategoryOptionsCategoryDialog
                )
            },
        )
    }
    
    if (state.ffqCategoryChartEntry.isNotEmpty()) {
        LineChart(
            entries = listOf(state.ffqCategoryChartEntry),
            xLabels = state.ffqCategoryChartXLabels,
            yLabels = state.ffqCategoryChartYLabels,
            labelFormatter = FfqCategoryLabelFormatter(state.ffqCategoryChartXLabels),
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Keterangan",
            style = MaterialTheme.typography.bodySmall,
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


@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ChartOptionsDialog(
    state: GraphState,
    viewModel: GraphViewModel,
) {
    val chartOptionsDialog = rememberUseCaseState(
        onCloseRequest = {
            viewModel.onEvent(
                GraphEvent.DismissDialog
            )
        }
    )
    ListDialog(
        state = chartOptionsDialog,
        selection = ListSelection.Single(
            options = when {
                state.showWeeklyAsaqOptionsWeekDialog -> {
                    state.weeklyAsaqOptionsWeek.map { week ->
                        ListOption(
                            titleText = "Minggu $week",
                            selected = week == state.weeklyAsaqSelectedWeek,
                        )
                    }
                }
                
                state.showWeeklyAsaqOptionsDayOfWeekDialog -> {
                    state.weeklyAsaqOptionsDayOfWeek.map { dayOfWeek ->
                        ListOption(
                            titleText = "Hari $dayOfWeek",
                            selected = dayOfWeek == state.weeklyAsaqSelectedDayOfWeek,
                        )
                    }
                }
                
                else -> {
                    state.ffqCategoryOptionsCategory.map { category ->
                        ListOption(
                            titleText = category.name,
                            selected = category.foodCategoryId == state.ffqCategorySelectedCategory,
                        )
                    }
                }
            }
        ) { selectedIndex, _ ->
            when {
                state.showWeeklyAsaqOptionsWeekDialog -> {
                    viewModel.onEvent(
                        GraphEvent.FilterWeeklyAsaqChartByWeek(
                            week = state.weeklyAsaqOptionsWeek[selectedIndex],
                        )
                    )
                }
                
                state.showWeeklyAsaqOptionsDayOfWeekDialog -> {
                    viewModel.onEvent(
                        GraphEvent.FilterWeeklyAsaqChartByDayOfWeek(
                            dayOfWeek = state.weeklyAsaqOptionsDayOfWeek[selectedIndex],
                        )
                    )
                }
                
                else -> {
                    viewModel.onEvent(
                        GraphEvent.FilterFfqCategoryChart(
                            foodCategoryId = state.ffqCategoryOptionsCategory[selectedIndex].foodCategoryId,
                        )
                    )
                }
            }
        }
    )
    
    LaunchedEffect(
        state.showWeeklyAsaqOptionsDayOfWeekDialog,
        state.showWeeklyAsaqOptionsWeekDialog,
        state.showFfqCategoryOptionsCategoryDialog,
    ) {
        if (state.showWeeklyAsaqOptionsDayOfWeekDialog ||
            state.showWeeklyAsaqOptionsWeekDialog ||
            state.showFfqCategoryOptionsCategoryDialog
        ) {
            chartOptionsDialog.show()
        }
    }
}