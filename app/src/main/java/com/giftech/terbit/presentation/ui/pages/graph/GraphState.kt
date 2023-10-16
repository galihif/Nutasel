package com.giftech.terbit.presentation.ui.pages.graph

import com.giftech.terbit.domain.model.FfqFoodCategory
import com.patrykandpatrick.vico.core.entry.ChartEntry

data class GraphState(
    val weeklyProgramProgress: Int,
    val weeklyAsaqOptionsWeek: List<Int>,
    val weeklyAsaqOptionsDayOfWeek: List<Int>,
    val weeklyAsaqSelectedWeek: Int,
    val weeklyAsaqSelectedDayOfWeek: Int,
    val preTestFfqScore: Int,
    val postTestFfqScore: Int,
    val ffqCategoryOptionsCategory: List<FfqFoodCategory>,
    val preTestFfqCategorySelectedCategory: Int,
    val postTestFfqCategorySelectedCategory: Int,

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

    val preTestFfqCategoryChartEntry: List<ChartEntry>,
    val postTestFfqCategoryChartEntry: List<ChartEntry>,
    val preTestFfqCategoryChartXLabels: List<String>,
    val postTestFfqCategoryChartXLabels: List<String>,
    val ffqCategoryChartYLabels: List<String>,
    
    val showWeeklyAsaqOptionsWeekDialog: Boolean,
    val showWeeklyAsaqOptionsDayOfWeekDialog: Boolean,
    val showPreTestFfqCategoryOptionsCategoryDialog: Boolean,
    val showPostTestFfqCategoryOptionsCategoryDialog: Boolean,
)