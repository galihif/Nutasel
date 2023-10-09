package com.giftech.terbit.presentation.ui.pages.graph

import com.giftech.terbit.domain.model.FfqFoodCategory
import com.patrykandpatrick.vico.core.entry.ChartEntry

data class GraphState(
    val weeklyProgramProgress: Int,
    val weeklyAsaqOptionsWeek: List<Int>,
    val weeklyAsaqOptionsDayOfWeek: List<Int>,
    val weeklyAsaqSelectedWeek: Int,
    val weeklyAsaqSelectedDayOfWeek: Int,
    val postTestFfqScore: Int,
    val ffqCategoryOptionsCategory: List<FfqFoodCategory>,
    val ffqCategorySelectedCategory: Int,

    val preTestAsaqChartEntry: List<ChartEntry>,
    val preTestAsaqChartXLabels: List<String>,
    val preTestAsaqChartMaxY: Int,
    val preTestAsaqChartYLabelCount: Int,

    val postTestAsaqChartEntry: List<ChartEntry>,
    val postTestAsaqChartXLabels: List<String>,
    val postTestAsaqChartMaxY: Int,
    val postTestAsaqChartYLabelCount: Int,

    val weeklyAsaqResponseChartEntry: List<ChartEntry>,
    val weeklyAsaqResponseChartXLabels: List<String>,
    val weeklyAsaqResponseChartMaxY: Int,
    val weeklyAsaqResponseChartYLabelCount: Int,

    val ffqScoreChartEntries: List<List<ChartEntry>>,
    val ffqScoreChartXLabels: List<String>,
    val ffqScoreChartMaxY: Int,
    val ffqScoreChartYLabelCount: Int,

    val ffqCategoryChartEntry: List<ChartEntry>,
    val ffqCategoryChartXLabels: List<String>,
    val ffqCategoryChartYLabels: List<String>,
    
    val showWeeklyAsaqOptionsWeekDialog: Boolean,
    val showWeeklyAsaqOptionsDayOfWeekDialog: Boolean,
    val showFfqCategoryOptionsCategoryDialog: Boolean,
)