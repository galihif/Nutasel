package com.giftech.terbit.presentation.ui.pages.graph

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.giftech.terbit.presentation.ui.theme.dark_CustomColor2
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
        PreTestFFQCategorySection(
            state = state,
            viewModel = viewModel,
        )
        Spacer(modifier = Modifier.height(40.dp))
        PostTestFFQCategorySection(
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
    WeeklyProgramProgress(
        weeklyProgramProgress = state.weeklyProgramProgress,
    )
}

@Composable
private fun PrePostTestAsaqSection(
    state: GraphState,
) {
    PreTestAsaq(
        preTestAsaqChartEntry = state.preTestAsaqChartEntry,
        preTestAsaqChartXLabels = state.preTestAsaqChartXLabels,
        preTestAsaqChartMaxY = state.preTestAsaqChartMaxY,
        preTestAsaqChartYLabelCount = state.preTestAsaqChartYLabelCount,
        preTestAsaqSedentaryAverageHours = state.preTestAsaqSedentaryAverageHours,
    )
    PostTestAsaq(
        preTestAsaqChartEntry = state.preTestAsaqChartEntry,
        postTestAsaqChartEntry = state.postTestAsaqChartEntry,
        postTestAsaqChartXLabels = state.postTestAsaqChartXLabels,
        postTestAsaqChartMaxY = state.postTestAsaqChartMaxY,
        postTestAsaqChartYLabelCount = state.postTestAsaqChartYLabelCount,
        postTestAsaqSedentaryAverageHours = state.postTestAsaqSedentaryAverageHours,
    )
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
    WeeklyAsaq(
        weeklyAsaqSelectedWeek = state.weeklyAsaqSelectedWeek,
        weeklyAsaqSelectedDayOfWeek = state.weeklyAsaqSelectedDayOfWeek,
        weeklyAsaqResponseChartEntry = state.weeklyAsaqResponseChartEntry,
        weeklyAsaqResponseChartXLabels = state.weeklyAsaqResponseChartXLabels,
        weeklyAsaqResponseChartMaxY = state.weeklyAsaqResponseChartMaxY,
        weeklyAsaqResponseChartYLabelCount = state.weeklyAsaqResponseChartYLabelCount,
        weeklyAsaqResponseSedentaryAverageHours = state.weeklyAsaqResponseSedentaryAverageHours,
        onSelectDayOfWeek = {
            viewModel.onEvent(
                GraphEvent.ShowWeeklyAsaqOptionsDayOfWeekDialog
            )
        },
        onSelectWeek = {
            viewModel.onEvent(
                GraphEvent.ShowWeeklyAsaqOptionsWeekDialog
            )
        },
    )
}

@Composable
private fun FFQScoreSection(
    state: GraphState,
) {
    Text(
        text = "Frekuensi Makanan",
        style = MaterialTheme.typography.titleMedium,
    )
    Spacer(modifier = Modifier.height(24.dp))
    FfqScore(
        preTestFfqScore = state.preTestFfqScore,
        postTestFfqScore = state.postTestFfqScore,
        ffqScoreChartEntries = state.ffqScoreChartEntries,
        ffqScoreChartXLabels = state.ffqScoreChartXLabels,
        ffqScoreChartMaxY = state.ffqScoreChartMaxY,
        ffqScoreChartYLabelCount = state.ffqScoreChartYLabelCount,
    )
}

@Composable
private fun PreTestFFQCategorySection(
    state: GraphState,
    viewModel: GraphViewModel,
) {
    Text(
        text = "Frekuensi Kategori Makanan Awal",
        style = MaterialTheme.typography.titleMedium,
    )
    Spacer(modifier = Modifier.height(16.dp))
    FfqCategory(
        ffqCategoryOptionsCategory = state.ffqCategoryOptionsCategory,
        ffqCategorySelectedCategory = state.preTestFfqCategorySelectedCategory,
        ffqCategoryChartEntry = state.preTestFfqCategoryChartEntry,
        ffqCategoryChartXLabels = state.preTestFfqCategoryChartXLabels,
        ffqCategoryChartYLabels = state.ffqCategoryChartYLabels,
        chartColors = listOf(MaterialTheme.colorScheme.primary),
        onSelectCategory = {
            viewModel.onEvent(
                GraphEvent.ShowPreTestFfqCategoryOptionsCategoryDialog
            )
        },
    )
}

@Composable
private fun PostTestFFQCategorySection(
    state: GraphState,
    viewModel: GraphViewModel,
) {
    Text(
        text = "Frekuensi Kategori Makanan Akhir",
        style = MaterialTheme.typography.titleMedium,
    )
    Spacer(modifier = Modifier.height(16.dp))
    FfqCategory(
        ffqCategoryOptionsCategory = state.ffqCategoryOptionsCategory,
        ffqCategorySelectedCategory = state.postTestFfqCategorySelectedCategory,
        ffqCategoryChartEntry = state.postTestFfqCategoryChartEntry,
        ffqCategoryChartXLabels = state.postTestFfqCategoryChartXLabels,
        ffqCategoryChartYLabels = state.ffqCategoryChartYLabels,
        chartColors = listOf(dark_CustomColor2),
        onSelectCategory = {
            viewModel.onEvent(
                GraphEvent.ShowPostTestFfqCategoryOptionsCategoryDialog
            )
        },
    )
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
                
                state.showPreTestFfqCategoryOptionsCategoryDialog -> {
                    state.ffqCategoryOptionsCategory.map { category ->
                        ListOption(
                            titleText = category.name,
                            selected = category.foodCategoryId == state.preTestFfqCategorySelectedCategory
                        )
                    }
                }
                
                else -> {
                    state.ffqCategoryOptionsCategory.map { category ->
                        ListOption(
                            titleText = category.name,
                            selected = category.foodCategoryId == state.postTestFfqCategorySelectedCategory
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
                
                state.showPreTestFfqCategoryOptionsCategoryDialog -> {
                    viewModel.onEvent(
                        GraphEvent.FilterPreTestFfqCategoryChart(
                            foodCategoryId = state.ffqCategoryOptionsCategory[selectedIndex].foodCategoryId,
                        )
                    )
                }
                
                else -> {
                    viewModel.onEvent(
                        GraphEvent.FilterPostTestFfqCategoryChart(
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
        state.showPreTestFfqCategoryOptionsCategoryDialog,
        state.showPostTestFfqCategoryOptionsCategoryDialog,
    ) {
        if (state.showWeeklyAsaqOptionsDayOfWeekDialog ||
            state.showWeeklyAsaqOptionsWeekDialog ||
            state.showPreTestFfqCategoryOptionsCategoryDialog ||
            state.showPostTestFfqCategoryOptionsCategoryDialog
        ) {
            chartOptionsDialog.show()
        }
    }
}