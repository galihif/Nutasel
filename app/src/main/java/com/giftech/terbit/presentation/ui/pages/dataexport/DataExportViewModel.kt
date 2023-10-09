package com.giftech.terbit.presentation.ui.pages.dataexport

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftech.terbit.domain.enums.FfqFrequency
import com.giftech.terbit.domain.usecase.GetFfqCategoryChartListUseCase
import com.giftech.terbit.domain.usecase.GetFfqFoodCategoryUseCase
import com.giftech.terbit.domain.usecase.GetFfqResultUseCase
import com.giftech.terbit.domain.usecase.GetFfqScoreChartUseCase
import com.giftech.terbit.domain.usecase.GetPostTestAsaqChartUseCase
import com.giftech.terbit.domain.usecase.GetPreTestAsaqChartUseCase
import com.giftech.terbit.domain.usecase.GetWeeklyAsaqChartListUseCase
import com.giftech.terbit.domain.usecase.GetWeeklyProgramProgressUseCase
import com.giftech.terbit.domain.usecase.UserUseCase
import com.giftech.terbit.domain.util.Constants
import com.giftech.terbit.domain.util.percentageOf
import com.patrykandpatrick.vico.core.entry.entryOf
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DataExportViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val getWeeklyProgramProgressUseCase: GetWeeklyProgramProgressUseCase,
    private val getFfqResultUseCase: GetFfqResultUseCase,
    private val getFfqFoodCategoryUseCase: GetFfqFoodCategoryUseCase,
    private val getPreTestAsaqChartUseCase: GetPreTestAsaqChartUseCase,
    private val getPostTestAsaqChartUseCase: GetPostTestAsaqChartUseCase,
    private val getWeeklyAsaqChartListUseCase: GetWeeklyAsaqChartListUseCase,
    private val getFfqScoreChartUseCase: GetFfqScoreChartUseCase,
    private val getFfqCategoryChartListUseCase: GetFfqCategoryChartListUseCase,
) : ViewModel() {
    
    private val _state = mutableStateOf(DataExportState())
    val state: State<DataExportState> = _state
    
    init {
        _state.value = _state.value.copy(
            currentPage = 1,
            totalPages = 40,
            startExtractingToBitmap = false,
            startSavingToStorage = false,
            allowedInteractions = true,
            bitmapExtractionProgress = 0,
        )
        viewModelScope.launch {
            userUseCase.getUser().collect { user ->
                _state.value = _state.value.copy(
                    userName = user.nama,
                    bodyHeight = user.tinggi,
                    bodyWeight = user.berat,
                    birthDate = user.tglLahir,
                    gender = if (user.isMale) "Laki-laki" else "Perempuan",
                )
            }
        }
        viewModelScope.launch {
            getWeeklyProgramProgressUseCase().collect {
                _state.value = _state.value.copy(
                    weeklyProgramProgress = it,
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
            getWeeklyAsaqChartListUseCase().collect { weeklyAsaqChartList ->
                val weeklyAsaqResponseChartEntryList = withContext(Dispatchers.IO) {
                    weeklyAsaqChartList.map { weeklyAsaqChart ->
                        weeklyAsaqChart.entry.map {
                            entryOf(
                                x = it.questionId,
                                y = it.freq.toDouble() / 60,
                            )
                        }
                    }
                }
                _state.value = _state.value.copy(
                    weeklyAsaqChartEntryList = weeklyAsaqResponseChartEntryList,
                    weeklyAsaqChartXLabels = weeklyAsaqChartList.firstOrNull()?.xLabels.orEmpty(),
                    weeklyAsaqChartMaxYList = weeklyAsaqChartList.map { it.maxY },
                    weeklyAsaqChartYLabelCount = weeklyAsaqChartList.firstOrNull()?.yLabelCount ?: 0,
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
        viewModelScope.launch {
            getFfqCategoryChartListUseCase().collect { ffqCategoryChartList ->
                val ffqCategoryChartEntryList = withContext(Dispatchers.IO) {
                    ffqCategoryChartList.map {
                        it.entry.mapIndexed { index, ffqResponse ->
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
                }
                _state.value = _state.value.copy(
                    ffqCategoryChartEntryList = ffqCategoryChartEntryList,
                    ffqCategoryChartXLabelsList = ffqCategoryChartList.map { it.xLabels },
                    ffqCategoryChartYLabels = ffqCategoryChartList.firstOrNull()?.yLabels.orEmpty(),
                )
            }
        }
    }
    
    fun onEvent(event: DataExportEvent) {
        when (event) {
            is DataExportEvent.NextPage -> {
                if (event.currentPage != _state.value.totalPages) {
                    _state.value = _state.value.copy(
                        currentPage = event.currentPage + 1
                    )
                }
                if (_state.value.startExtractingToBitmap) {
                    _state.value = _state.value.copy(
                        bitmapExtractionProgress = percentageOf(
                            value = event.currentPage,
                            total = _state.value.totalPages,
                        ),
                    )
                }
            }
            
            is DataExportEvent.PreviousPage -> {
                if (event.currentPage != 1) {
                    _state.value = _state.value.copy(
                        currentPage = event.currentPage - 1
                    )
                }
            }
            
            is DataExportEvent.StartExtractingToBitmap -> {
                _state.value = _state.value.copy(
                    startExtractingToBitmap = true,
                    allowedInteractions = false,
                    currentPage = 1,
                    bitmapExtractionProgress = 0,
                )
            }
            
            is DataExportEvent.FinishExtractingToBitmap -> {
                _state.value = _state.value.copy(
                    startExtractingToBitmap = false,
                )
            }
            
            is DataExportEvent.StartSavingToStorage -> {
                _state.value = _state.value.copy(
                    startSavingToStorage = true,
                )
            }
            
            is DataExportEvent.FinishSavingToStorage -> {
                _state.value = _state.value.copy(
                    startSavingToStorage = false,
                    allowedInteractions = true,
                    currentPage = 1,
                    bitmapExtractionProgress = 0,
                )
            }
        }
    }
    
}