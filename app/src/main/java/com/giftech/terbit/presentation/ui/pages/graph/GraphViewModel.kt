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
            postTestFfqScore = -1,
            ffqCategoryOptionsCategory = emptyList(),
            ffqCategorySelectedCategory = -1,
            
            preTestAsaqChartEntry = emptyList(),
            preTestAsaqChartXLabels = emptyList(),
            preTestAsaqChartMaxY = 0,
            preTestAsaqChartYLabelCount = 0,
            
            postTestAsaqChartEntry = emptyList(),
            postTestAsaqChartXLabels = emptyList(),
            postTestAsaqChartMaxY = 0,
            postTestAsaqChartYLabelCount = 0,
            
            weeklyAsaqResponseChartEntry = emptyList(),
            weeklyAsaqResponseChartXLabels = emptyList(),
            weeklyAsaqResponseChartMaxY = 0,
            weeklyAsaqResponseChartYLabelCount = 0,
            
            ffqScoreChartEntries = emptyList(),
            ffqScoreChartXLabels = emptyList(),
            ffqScoreChartMaxY = 0,
            ffqScoreChartYLabelCount = 0,
            
            ffqCategoryChartEntry = emptyList(),
            ffqCategoryChartXLabels = emptyList(),
            ffqCategoryChartYLabels = emptyList(),
            
            showWeeklyAsaqOptionsWeekDialog = false,
            showWeeklyAsaqOptionsDayOfWeekDialog = false,
            showFfqCategoryOptionsCategoryDialog = false,
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
                getFfqCategoryChart(
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
                            y = ((it.durasiHariKerja * 5 + it.durasiHariLibur * 2) / 7).toDouble() / 60,
                        )
                    }
                }
                _state.value = _state.value.copy(
                    preTestAsaqChartEntry = preTestAsaqChartEntry,
                    preTestAsaqChartXLabels = preTestAsaqChart.xLabels,
                    preTestAsaqChartMaxY = preTestAsaqChart.maxY,
                    preTestAsaqChartYLabelCount = preTestAsaqChart.yLabelCount,
                )
            }
        }
        viewModelScope.launch {
            getPostTestAsaqChartUseCase().collect { postTestAsaqChart ->
                val postTestAsaqChartEntry = withContext(Dispatchers.IO) {
                    postTestAsaqChart.entry.map {
                        entryOf(
                            x = it.questionId,
                            y = ((it.durasiHariKerja * 5 + it.durasiHariLibur * 2) / 7).toDouble() / 60,
                        )
                    }
                }
                _state.value = _state.value.copy(
                    postTestAsaqChartEntry = postTestAsaqChartEntry,
                    postTestAsaqChartXLabels = postTestAsaqChart.xLabels,
                    postTestAsaqChartMaxY = postTestAsaqChart.maxY,
                    postTestAsaqChartYLabelCount = postTestAsaqChart.yLabelCount,
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
                            )
                        },
                        groupedEntries2.map { (foodCategoryId, ffqResponseList) ->
                            entryOf(
                                x = groupedEntries2.keys.indexOf(foodCategoryId),
                                y = ffqResponseList.sumOf { it.freq?.score ?: 0 }
                            )
                        },
                    )
                }
                _state.value = _state.value.copy(
                    ffqScoreChartEntries = ffqScoreChartEntries,
                    ffqScoreChartXLabels = ffqScoreChart.xLabels,
                    ffqScoreChartMaxY = ffqScoreChart.maxY,
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
            
            is GraphEvent.FilterFfqCategoryChart -> {
                getFfqCategoryChart(
                    ffqFoodCategoryId = event.foodCategoryId,
                )
            }
            
            is GraphEvent.ShowFfqCategoryOptionsCategoryDialog -> {
                _state.value = _state.value.copy(
                    showFfqCategoryOptionsCategoryDialog = true,
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
                    showFfqCategoryOptionsCategoryDialog = false,
                )
            }
        }
    }
    
    private fun getWeeklyAsaqChart(week: Int, dayOfWeek: Int) {
        viewModelScope.launch {
            getWeeklyAsaqChartUseCase(
                week = week,
                dayOfWeek = dayOfWeek,
            ).collect { weeklyAsaqChart ->
                val weeklyAsaqResponseChartEntry = withContext(Dispatchers.IO) {
                    weeklyAsaqChart.entry.map {
                        entryOf(
                            x = it.questionId,
                            y = it.freq.toDouble() / 60,
                        )
                    }
                }
                _state.value = _state.value.copy(
                    weeklyAsaqResponseChartEntry = weeklyAsaqResponseChartEntry,
                    weeklyAsaqResponseChartXLabels = weeklyAsaqChart.xLabels,
                    weeklyAsaqResponseChartMaxY = weeklyAsaqChart.maxY,
                    weeklyAsaqResponseChartYLabelCount = weeklyAsaqChart.yLabelCount,
                    weeklyAsaqSelectedWeek = week,
                    weeklyAsaqSelectedDayOfWeek = dayOfWeek,
                )
            }
        }
    }
    
    private fun getFfqCategoryChart(ffqFoodCategoryId: Int) {
        viewModelScope.launch {
            getFfqCategoryChartUseCase(
                ffqFoodCategoryId = ffqFoodCategoryId,
            ).collect { ffqCategoryChart ->
                val ffqCategoryChartEntry = withContext(Dispatchers.IO) {
                    ffqCategoryChart.entry.mapIndexed { index, ffqResponse ->
                        entryOf(
                            x = index,
                            y = when (ffqResponse.freq ?: FfqFrequency.NEVER) {
                                FfqFrequency.NEVER -> 0
                                FfqFrequency.DAY_1 -> 1
                                FfqFrequency.DAY_3 -> 2
                                FfqFrequency.WEEK_1_2 -> 3
                                FfqFrequency.WEEK_3_6 -> 4
                                FfqFrequency.MONTH_2 -> 5
                            },
                        )
                    }
                }
                _state.value = _state.value.copy(
                    ffqCategoryChartEntry = ffqCategoryChartEntry,
                    ffqCategoryChartXLabels = ffqCategoryChart.xLabels,
                    ffqCategoryChartYLabels = ffqCategoryChart.yLabels,
                    ffqCategorySelectedCategory = ffqFoodCategoryId,
                )
            }
        }
    }
    
}