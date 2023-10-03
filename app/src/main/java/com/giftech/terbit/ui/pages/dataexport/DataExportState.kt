package com.giftech.terbit.ui.pages.dataexport

import com.giftech.terbit.domain.model.FfqFoodCategory
import com.patrykandpatrick.vico.core.entry.ChartEntry

data class DataExportState(
    val currentPage: Int = -1,
    val totalPages: Int = -1,
    
    val userName: String = "",
    val bodyHeight: Int = -1,
    val bodyWeight: Int = -1,
    val birthDate: String = "",
    val gender: String = "",
    
    val weeklyProgramProgress: Int = 0,
    
    val preTestAsaqChartEntry: List<ChartEntry> = emptyList(),
    val preTestAsaqChartXLabels: List<String> = emptyList(),
    val preTestAsaqChartMaxY: Int = 0,
    val preTestAsaqChartYLabelCount: Int = 0,
    
    val postTestAsaqChartEntry: List<ChartEntry> = emptyList(),
    val postTestAsaqChartXLabels: List<String> = emptyList(),
    val postTestAsaqChartMaxY: Int = 0,
    val postTestAsaqChartYLabelCount: Int = 0,
    
    val weeklyAsaqChartEntryList: List<List<ChartEntry>> = emptyList(),
    val weeklyAsaqChartXLabels: List<String> = emptyList(),
    val weeklyAsaqChartMaxYList: List<Int> = emptyList(),
    val weeklyAsaqChartYLabelCount: Int = 0,
    
    val postTestFfqScore: Int = 0,
    val ffqScoreChartEntries: List<List<ChartEntry>> = emptyList(),
    val ffqScoreChartXLabels: List<String> = emptyList(),
    val ffqScoreChartMaxY: Int = 0,
    val ffqScoreChartYLabelCount: Int = 0,
    
    val ffqCategoryChartEntryList: List<List<ChartEntry>> = emptyList(),
    val ffqCategoryChartXLabelsList: List<List<String>> = emptyList(),
    val ffqCategoryChartYLabels: List<String> = emptyList(),
    val ffqCategoryOptionsCategory: List<FfqFoodCategory> = emptyList(),
    
    val startExtractingToBitmap: Boolean = false,
    val startSavingToStorage: Boolean = false,
    val allowedInteractions: Boolean = true,
    val bitmapExtractionProgress: Int = 0,
)