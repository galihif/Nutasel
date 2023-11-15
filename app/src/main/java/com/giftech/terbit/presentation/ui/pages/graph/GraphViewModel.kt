package com.giftech.terbit.presentation.ui.pages.graph

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftech.terbit.domain.enums.FfqFrequency
import com.giftech.terbit.domain.usecase.GetFfqCategoryChartUseCase
import com.giftech.terbit.domain.usecase.GetFfqFoodCategoryUseCase
import com.giftech.terbit.domain.usecase.GetFfqResultUseCase
import com.giftech.terbit.domain.usecase.GetFfqScoreChartUseCase
import com.giftech.terbit.domain.usecase.GetPostTestAsaqChartUseCase
import com.giftech.terbit.domain.usecase.GetPreTestAsaqChartUseCase
import com.giftech.terbit.domain.usecase.GetWeeklyAsaqChartOptionsUseCase
import com.giftech.terbit.domain.usecase.GetWeeklyAsaqChartUseCase
import com.giftech.terbit.domain.usecase.GetWeeklyProgramProgressUseCase
import com.giftech.terbit.domain.util.Constants
import com.patrykandpatrick.vico.core.entry.entryOf
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GraphViewModel @Inject constructor(
    private val getWeeklyProgramProgressUseCase: GetWeeklyProgramProgressUseCase,
    getWeeklyAsaqChartOptionsUseCase: GetWeeklyAsaqChartOptionsUseCase,
    private val getFfqResultUseCase: GetFfqResultUseCase,
    private val getFfqFoodCategoryUseCase: GetFfqFoodCategoryUseCase,
    private val getPreTestAsaqChartUseCase: GetPreTestAsaqChartUseCase,
    private val getPostTestAsaqChartUseCase: GetPostTestAsaqChartUseCase,
    private val getWeeklyAsaqChartUseCase: GetWeeklyAsaqChartUseCase,
    private val getFfqScoreChartUseCase: GetFfqScoreChartUseCase,
    private val getFfqCategoryChartUseCase: GetFfqCategoryChartUseCase,
) : ViewModel() {
    
    private val _state = mutableStateOf(
        GraphState(
            weeklyProgramProgress = 0,
            weeklyAsaqOptionsWeek = emptyList(),
            weeklyAsaqOptionsDayOfWeek = emptyList(),
            weeklyAsaqSelectedWeek = -1,
            weeklyAsaqSelectedDayOfWeek = -1,
            preTestFfqScore = -1,
            postTestFfqScore = -1,
            ffqCategoryOptionsCategory = emptyList(),
            preTestFfqCategorySelectedCategory = -1,
            postTestFfqCategorySelectedCategory = -1,
            
            preTestAsaqChartEntry = emptyList(),
            preTestAsaqChartXLabels = emptyList(),
            preTestAsaqChartMaxY = 0,
            preTestAsaqChartYLabelCount = 0,
            preTestAsaqSedentaryAverageHours = 0.0,
            
            postTestAsaqChartEntry = emptyList(),
            postTestAsaqChartXLabels = emptyList(),
            postTestAsaqChartMaxY = 0,
            postTestAsaqChartYLabelCount = 0,
            postTestAsaqSedentaryAverageHours = 0.0,
            
            weeklyAsaqResponseChartEntry = emptyList(),
            weeklyAsaqResponseChartXLabels = emptyList(),
            weeklyAsaqResponseChartMaxY = 0,
            weeklyAsaqResponseChartYLabelCount = 0,
            weeklyAsaqResponseSedentaryAverageHours = 0.0,
            
            ffqScoreChartEntries = emptyList(),
            ffqScoreChartXLabels = emptyList(),
            ffqScoreChartMaxY = 0,
            ffqScoreChartYLabelCount = 0,
            
            preTestFfqCategoryChartEntry = emptyList(),
            postTestFfqCategoryChartEntry = emptyList(),
            preTestFfqCategoryChartXLabels = emptyList(),
            postTestFfqCategoryChartXLabels = emptyList(),
            ffqCategoryChartYLabels = emptyList(),
            
            showWeeklyAsaqOptionsWeekDialog = false,
            showWeeklyAsaqOptionsDayOfWeekDialog = false,
            showPreTestFfqCategoryOptionsCategoryDialog = false,
            showPostTestFfqCategoryOptionsCategoryDialog = false,
        )
    )
    val state: State<GraphState> = _state
    
    init {
        viewModelScope.launch {
            getWeeklyProgramProgressUseCase().collect {
                _state.value = _state.value.copy(
                    weeklyProgramProgress = it,
                )
            }
        }
        getWeeklyAsaqChartOptionsUseCase().let { (weekOptions, dayOfWeekOptions) ->
            _state.value = _state.value.copy(
                weeklyAsaqOptionsWeek = weekOptions,
                weeklyAsaqOptionsDayOfWeek = dayOfWeekOptions,
            )
            getWeeklyAsaqChart(
                week = weekOptions.first(),
                dayOfWeek = dayOfWeekOptions.first(),
            )
        }
        viewModelScope.launch {
            getFfqResultUseCase(
                programId = Constants.ProgramId.FIRST_FFQ,
            ).collect {
                _state.value = _state.value.copy(
                    preTestFfqScore = it,
                )
            }
        }
        viewModelScope.launch {
            getFfqResultUseCase(
                programId = Constants.ProgramId.LAST_FFQ,
            ).collect {
                _state.value = _state.value.copy(
                    postTestFfqScore = it,
                )
            }
        }
        viewModelScope.launch {
            getFfqFoodCategoryUseCase().collect { foodCategoryList ->
                _state.value = _state.value.copy(
                    ffqCategoryOptionsCategory = foodCategoryList,
                )
                getPreTestFfqCategoryChart(
                    ffqFoodCategoryId = foodCategoryList.first().foodCategoryId,
                )
                getPostTestFfqCategoryChart(
                    ffqFoodCategoryId = foodCategoryList.first().foodCategoryId,
                )
            }
        }
        viewModelScope.launch {
            getPreTestAsaqChartUseCase().collect { preTestAsaqChart ->
                val preTestAsaqChartEntry = withContext(Dispatchers.IO) {
                    preTestAsaqChart.entry.map {
                        entryOf(
                            x = it.questionId,
                            y = (((it.durasiHariKerja * 5 + it.durasiHariLibur * 2) / 7).toDouble() / 60)
                                .takeIf { freqHours -> freqHours > 0.0 } ?: 0.01,
                        )
                    }
                }
                _state.value = _state.value.copy(
                    preTestAsaqChartEntry = preTestAsaqChartEntry,
                    preTestAsaqChartXLabels = preTestAsaqChart.xLabels,
                    preTestAsaqChartMaxY = preTestAsaqChart.maxY,
                    preTestAsaqChartYLabelCount = preTestAsaqChart.yLabelCount,
                    preTestAsaqSedentaryAverageHours = preTestAsaqChart.sedentaryAverageHours,
                )
            }
        }
        viewModelScope.launch {
            getPostTestAsaqChartUseCase().collect { postTestAsaqChart ->
                val postTestAsaqChartEntry = withContext(Dispatchers.IO) {
                    postTestAsaqChart.entry.map {
                        entryOf(
                            x = it.questionId,
                            y = (((it.durasiHariKerja * 5 + it.durasiHariLibur * 2) / 7).toDouble() / 60)
                                .takeIf { freqHours -> freqHours > 0.0 } ?: 0.01,
                        )
                    }
                }
                _state.value = _state.value.copy(
                    postTestAsaqChartEntry = postTestAsaqChartEntry,
                    postTestAsaqChartXLabels = postTestAsaqChart.xLabels,
                    postTestAsaqChartMaxY = postTestAsaqChart.maxY,
                    postTestAsaqChartYLabelCount = postTestAsaqChart.yLabelCount,
                    postTestAsaqSedentaryAverageHours = postTestAsaqChart.sedentaryAverageHours,
                )
            }
        }
        viewModelScope.launch {
            getFfqScoreChartUseCase().collect { ffqScoreChart ->
                val ffqScoreChartEntries = withContext(Dispatchers.IO) {
                    val groupedEntries1 = ffqScoreChart.entry1.groupBy { it.foodCategoryId }
                    val groupedEntries2 = ffqScoreChart.entry2.groupBy { it.foodCategoryId }
                    listOf(
                        groupedEntries1.map { (foodCategoryId, ffqResponseList) ->
                            entryOf(
                                x = groupedEntries1.keys.indexOf(foodCategoryId),
                                y = ffqResponseList.sumOf { it.freq?.score ?: 0 }
                                    .takeIf { it > 0 } ?: 0.01,
                            )
                        },
                        groupedEntries2.map { (foodCategoryId, ffqResponseList) ->
                            entryOf(
                                x = groupedEntries2.keys.indexOf(foodCategoryId),
                                y = ffqResponseList.sumOf { it.freq?.score ?: 0 }
                                    .takeIf { it > 0 } ?: 0.01,
                            )
                        },
                    )
                }
                _state.value = _state.value.copy(
                    ffqScoreChartEntries = ffqScoreChartEntries,
                    ffqScoreChartXLabels = ffqScoreChart.xLabels,
                    ffqScoreChartMaxY = if (ffqScoreChartEntries[0].maxOf { it.y } <= 300 &&
                        ffqScoreChartEntries[1].maxOf { it.y } <= 300) 300 else 600,
                    ffqScoreChartYLabelCount = ffqScoreChart.yLabelCount,
                )
            }
        }
    }
    
    fun onEvent(event: GraphEvent) {
        when (event) {
            is GraphEvent.FilterWeeklyAsaqChartByWeek -> {
                getWeeklyAsaqChart(
                    week = event.week,
                    dayOfWeek = _state.value.weeklyAsaqSelectedDayOfWeek,
                )
            }
            
            is GraphEvent.FilterWeeklyAsaqChartByDayOfWeek -> {
                getWeeklyAsaqChart(
                    week = _state.value.weeklyAsaqSelectedWeek,
                    dayOfWeek = event.dayOfWeek,
                )
            }
            
            is GraphEvent.FilterPreTestFfqCategoryChart -> {
                getPreTestFfqCategoryChart(
                    ffqFoodCategoryId = event.foodCategoryId,
                )
            }
            
            is GraphEvent.FilterPostTestFfqCategoryChart -> {
                getPostTestFfqCategoryChart(
                    ffqFoodCategoryId = event.foodCategoryId,
                )
            }
            
            is GraphEvent.ShowPreTestFfqCategoryOptionsCategoryDialog -> {
                _state.value = _state.value.copy(
                    showPreTestFfqCategoryOptionsCategoryDialog = true,
                )
            }
            
            is GraphEvent.ShowPostTestFfqCategoryOptionsCategoryDialog -> {
                _state.value = _state.value.copy(
                    showPostTestFfqCategoryOptionsCategoryDialog = true,
                )
            }
            
            is GraphEvent.ShowWeeklyAsaqOptionsDayOfWeekDialog -> {
                _state.value = _state.value.copy(
                    showWeeklyAsaqOptionsDayOfWeekDialog = true,
                )
            }
            
            is GraphEvent.ShowWeeklyAsaqOptionsWeekDialog -> {
                _state.value = _state.value.copy(
                    showWeeklyAsaqOptionsWeekDialog = true,
                )
            }
            
            GraphEvent.DismissDialog -> {
                _state.value = _state.value.copy(
                    showWeeklyAsaqOptionsWeekDialog = false,
                    showWeeklyAsaqOptionsDayOfWeekDialog = false,
                    showPreTestFfqCategoryOptionsCategoryDialog = false,
                    showPostTestFfqCategoryOptionsCategoryDialog = false,
                )
            }
        }
    }
    
    private fun getWeeklyAsaqChart(
        week: Int,
        dayOfWeek: Int,
    ) {
        viewModelScope.launch {
            getWeeklyAsaqChartUseCase(
                week = week,
                dayOfWeek = dayOfWeek,
            ).collect { weeklyAsaqChart ->
                val weeklyAsaqResponseChartEntry = withContext(Dispatchers.IO) {
                    weeklyAsaqChart.entry.map {
                        entryOf(
                            x = it.questionId,
                            y = (it.freq.toDouble() / 60)
                                .takeIf { freqHours -> freqHours > 0.0 } ?: 0.01,
                        )
                    }
                }
                _state.value = _state.value.copy(
                    weeklyAsaqResponseChartEntry = weeklyAsaqResponseChartEntry,
                    weeklyAsaqResponseChartXLabels = weeklyAsaqChart.xLabels,
                    weeklyAsaqResponseChartMaxY = weeklyAsaqChart.maxY,
                    weeklyAsaqResponseChartYLabelCount = weeklyAsaqChart.yLabelCount,
                    weeklyAsaqResponseSedentaryAverageHours = weeklyAsaqChart.sedentaryAverageHours,
                    weeklyAsaqSelectedWeek = week,
                    weeklyAsaqSelectedDayOfWeek = dayOfWeek,
                )
            }
        }
    }
    
    private fun getPreTestFfqCategoryChart(ffqFoodCategoryId: Int) {
        viewModelScope.launch {
            getFfqCategoryChartUseCase(
                ffqProgramId = Constants.ProgramId.FIRST_FFQ,
                ffqFoodCategoryId = ffqFoodCategoryId,
            ).collect { ffqCategoryChart ->
                val ffqCategoryChartEntry = withContext(Dispatchers.IO) {
                    ffqCategoryChart.entry.mapIndexed { index, ffqResponse ->
                        entryOf(
                            x = index,
                            y = when (ffqResponse.freq ?: FfqFrequency.NEVER) {
                                FfqFrequency.NEVER -> 0
                                FfqFrequency.MONTH_2 -> 1
                                FfqFrequency.WEEK_1_2 -> 2
                                FfqFrequency.WEEK_3_6 -> 3
                                FfqFrequency.DAY_1 -> 4
                                FfqFrequency.DAY_3 -> 5
                            },
                        )
                    }
                }
                _state.value = _state.value.copy(
                    preTestFfqCategoryChartEntry = ffqCategoryChartEntry,
                    preTestFfqCategoryChartXLabels = ffqCategoryChart.xLabels,
                    ffqCategoryChartYLabels = ffqCategoryChart.yLabels,
                    preTestFfqCategorySelectedCategory = ffqFoodCategoryId,
                )
            }
        }
    }
    
    private fun getPostTestFfqCategoryChart(ffqFoodCategoryId: Int) {
        viewModelScope.launch {
            getFfqCategoryChartUseCase(
                ffqProgramId = Constants.ProgramId.LAST_FFQ,
                ffqFoodCategoryId = ffqFoodCategoryId,
            ).collect { ffqCategoryChart ->
                val ffqCategoryChartEntry = withContext(Dispatchers.IO) {
                    ffqCategoryChart.entry.mapIndexed { index, ffqResponse ->
                        entryOf(
                            x = index,
                            y = when (ffqResponse.freq ?: FfqFrequency.NEVER) {
                                FfqFrequency.NEVER -> 0
                                FfqFrequency.MONTH_2 -> 1
                                FfqFrequency.WEEK_1_2 -> 2
                                FfqFrequency.WEEK_3_6 -> 3
                                FfqFrequency.DAY_1 -> 4
                                FfqFrequency.DAY_3 -> 5
                            },
                        )
                    }
                }
                _state.value = _state.value.copy(
                    postTestFfqCategoryChartEntry = ffqCategoryChartEntry,
                    postTestFfqCategoryChartXLabels = ffqCategoryChart.xLabels,
                    postTestFfqCategorySelectedCategory = ffqFoodCategoryId,
                )
            }
        }
    }
    
}