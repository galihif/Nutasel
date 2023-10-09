package com.giftech.terbit.presentation.ui.pages.dataexport

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.ParcelFileDescriptor
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.NavigateBefore
import androidx.compose.material.icons.rounded.NavigateNext
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.documentfile.provider.DocumentFile
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.giftech.terbit.domain.model.FfqFoodCategory
import com.giftech.terbit.domain.util.Constants
import com.giftech.terbit.domain.util.toString
import com.giftech.terbit.presentation.ui.components.molecules.AppBar
import com.giftech.terbit.presentation.ui.pages.graph.FfqCategory
import com.giftech.terbit.presentation.ui.pages.graph.FfqScore
import com.giftech.terbit.presentation.ui.pages.graph.PostTestAsaq
import com.giftech.terbit.presentation.ui.pages.graph.PreTestAsaq
import com.giftech.terbit.presentation.ui.pages.graph.WeeklyAsaq
import com.giftech.terbit.presentation.ui.pages.graph.WeeklyProgramProgress
import com.giftech.terbit.presentation.ui.pages.profile.ProfileTextColumn
import com.giftech.terbit.presentation.util.LockScreenOrientation
import com.giftech.terbit.presentation.util.showToast
import com.patrykandpatrick.vico.core.entry.ChartEntry
import dev.shreyaspatil.capturable.Capturable
import dev.shreyaspatil.capturable.controller.rememberCaptureController
import kotlinx.coroutines.delay
import java.io.FileOutputStream
import java.time.LocalDateTime

@Composable
fun DataExportScreen(
    navController: NavController,
    viewModel: DataExportViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    LockScreenOrientation(
        orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT,
    )
    
    val state = viewModel.state.value
    
    val pageBitmapList = remember {
        mutableListOf<ImageBitmap>()
    }
    val onExportResult = remember {
        { bitmap: ImageBitmap?, e: Throwable? ->
            if (bitmap != null) {
                pageBitmapList.add(bitmap)
            }
            if (e != null) {
                showToast(
                    context = context,
                    message = e.message ?: e.localizedMessage,
                )
            }
        }
    }
    
    var selectedDirectoryUri: Uri? by remember {
        mutableStateOf(null)
    }
    val directoryPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocumentTree(),
        onResult = { uri ->
            uri?.let {
                val takeFlags = Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                context.contentResolver.takePersistableUriPermission(
                    uri,
                    takeFlags,
                )
                selectedDirectoryUri = uri
            }
        }
    )
    
    LaunchedEffect(
        state.startSavingToStorage,
        selectedDirectoryUri,
    ) {
        if (state.startSavingToStorage) {
            if (selectedDirectoryUri == null) {
                directoryPicker.launch(null)
            } else {
                val pdfDocument = buildPdfDocument(
                    bitmapList = pageBitmapList,
                )
                pageBitmapList.clear()
                
                val fileName = "Laporan Pemantauan ${state.userName} - ${
                    LocalDateTime.now().toString(Constants.DatePattern.FILE_NAME)
                }.pdf"
                saveToStorage(
                    context = context,
                    pdfDocument = pdfDocument,
                    fileName = fileName,
                    directoryPathUri = selectedDirectoryUri!!,
                )
                
                viewModel.onEvent(
                    DataExportEvent.FinishSavingToStorage
                )
                
                showToast(
                    context = context,
                    message = "Laporan berhasil disimpan.",
                )
            }
        }
    }
    
    DataExportContent(
        state = state,
        viewModel = viewModel,
        navController = navController,
        onExportResult = onExportResult,
    )
}

@Composable
private fun DataExportContent(
    state: DataExportState,
    viewModel: DataExportViewModel,
    navController: NavController,
    onExportResult: (ImageBitmap?, Throwable?) -> Unit,
) {
    Scaffold(
        topBar = {
            AppBar(
                title = "Preview Laporan",
                onBack = {
                    navController.popBackStack()
                },
            )
        },
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(24.dp),
        ) {
            PageSection(
                state = state,
                viewModel = viewModel,
                onExportResult = onExportResult,
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = {
                        viewModel.onEvent(
                            DataExportEvent.PreviousPage(
                                currentPage = state.currentPage,
                            )
                        )
                    },
                    enabled = state.allowedInteractions,
                ) {
                    Icon(
                        imageVector = Icons.Rounded.NavigateBefore,
                        contentDescription = "Halaman sebelumnya",
                    )
                }
                Text(
                    text = "Halaman ${state.currentPage}/${state.totalPages}",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f),
                )
                IconButton(
                    onClick = {
                        viewModel.onEvent(
                            DataExportEvent.NextPage(
                                currentPage = state.currentPage,
                            )
                        )
                    },
                    enabled = state.allowedInteractions,
                ) {
                    Icon(
                        imageVector = Icons.Rounded.NavigateNext,
                        contentDescription = "Halaman selanjutnya",
                    )
                }
            }
            
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier
                    .weight(1f),
            ) {
                Button(
                    onClick = {
                        viewModel.onEvent(
                            DataExportEvent.StartExtractingToBitmap
                        )
                    },
                    enabled = state.allowedInteractions,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                ) {
                    Text(
                        if (state.allowedInteractions) "Simpan File"
                        else "Menyimpan File... ${state.bitmapExtractionProgress}%",
                    )
                }
            }
        }
    }
}

@Composable
private fun PageSection(
    state: DataExportState,
    viewModel: DataExportViewModel,
    onExportResult: (ImageBitmap?, Throwable?) -> Unit,
) {
    val captureController = rememberCaptureController()
    
    Frame {
        Capturable(
            controller = captureController,
            onCaptured = { bitmap, e ->
                onExportResult(
                    bitmap,
                    e,
                )
            },
        ) {
            // TODO: Still don't know how to not use hardcoded page number
            when (state.currentPage) {
                1 -> {
                    PageSection1(
                        pageNumber = state.currentPage,
                        userName = state.userName,
                        bodyHeight = state.bodyHeight,
                        bodyWeight = state.bodyWeight,
                        birthDate = state.birthDate,
                        gender = state.gender,
                    )
                }
                
                2 -> {
                    PageSection2(
                        pageNumber = state.currentPage,
                        weeklyProgramProgress = state.weeklyProgramProgress,
                    )
                }
                
                3 -> {
                    PageSection3(
                        pageNumber = state.currentPage,
                        preTestAsaqChartEntry = state.preTestAsaqChartEntry,
                        preTestAsaqChartXLabels = state.preTestAsaqChartXLabels,
                        preTestAsaqChartMaxY = state.preTestAsaqChartMaxY,
                        preTestAsaqChartYLabelCount = state.preTestAsaqChartYLabelCount,
                    )
                }
                
                4 -> {
                    PageSection4(
                        pageNumber = state.currentPage,
                        preTestAsaqChartEntry = state.preTestAsaqChartEntry,
                        postTestAsaqChartEntry = state.postTestAsaqChartEntry,
                        postTestAsaqChartXLabels = state.postTestAsaqChartXLabels,
                        postTestAsaqChartMaxY = state.postTestAsaqChartMaxY,
                        postTestAsaqChartYLabelCount = state.postTestAsaqChartYLabelCount,
                    )
                }
                
                5, 6, 7, 8, 9, 10, 11,
                12, 13, 14, 15, 16, 17, 18,
                19, 20, 21, 22, 23, 24, 25,
                26, 27, 28, 29, 30, 31, 32,
                -> {
                    val day = state.currentPage - 4
                    val index = day - 1
                    val weeklyAsaqSelectedWeek = if (day % 7 == 0) {
                        day / 7
                    } else {
                        day / 7 + 1
                    }
                    val weeklyAsaqSelectedDayOfWeek = if (day % 7 == 0) {
                        7
                    } else {
                        day % 7
                    }
                    PageSection5(
                        pageNumber = state.currentPage,
                        weeklyAsaqChartEntry = state.weeklyAsaqChartEntryList.getOrElse(index) { emptyList() },
                        weeklyAsaqChartXLabels = state.weeklyAsaqChartXLabels,
                        weeklyAsaqChartMaxY = state.weeklyAsaqChartMaxYList.getOrElse(index) { 0 },
                        weeklyAsaqChartYLabelCount = state.weeklyAsaqChartYLabelCount,
                        weeklyAsaqSelectedWeek = weeklyAsaqSelectedWeek,
                        weeklyAsaqSelectedDayOfWeek = weeklyAsaqSelectedDayOfWeek,
                        showTitle = index == 0,
                    )
                }
                
                33 -> {
                    PageSection6(
                        pageNumber = state.currentPage,
                        postTestFfqScore = state.postTestFfqScore,
                        ffqScoreChartEntries = state.ffqScoreChartEntries,
                        ffqScoreChartXLabels = state.ffqScoreChartXLabels,
                        ffqScoreChartMaxY = state.ffqScoreChartMaxY,
                        ffqScoreChartYLabelCount = state.ffqScoreChartYLabelCount,
                    )
                }
                
                34, 35, 36, 37, 38, 39, 40 -> {
                    val index = state.currentPage - 34
                    PageSection7(
                        pageNumber = state.currentPage,
                        ffqCategoryChartEntry = state.ffqCategoryChartEntryList.getOrElse(index) { emptyList() },
                        ffqCategoryChartXLabels = state.ffqCategoryChartXLabelsList.getOrElse(index) { emptyList() },
                        ffqCategoryChartYLabels = state.ffqCategoryChartYLabels,
                        ffqCategoryOptionsCategory = state.ffqCategoryOptionsCategory,
                        ffqCategorySelectedCategory = state.ffqCategoryOptionsCategory.getOrNull(index)?.foodCategoryId
                            ?: -1,
                        showTitle = index == 0,
                    )
                }
            }
        }
    }
    
    LaunchedEffect(
        state.startExtractingToBitmap,
        state.currentPage,
    ) {
        if (state.startExtractingToBitmap) {
            // Give some time for the UI to render the current page
            delay(200)
            captureController.capture()
            
            val currentPage = state.currentPage
            
            // Change to next page after capturing the current page
            viewModel.onEvent(
                DataExportEvent.NextPage(
                    currentPage = currentPage,
                )
            )
            
            val isLastPage = currentPage == state.totalPages
            if (isLastPage) {
                viewModel.onEvent(
                    DataExportEvent.FinishExtractingToBitmap
                )
                viewModel.onEvent(
                    DataExportEvent.StartSavingToStorage
                )
            }
        }
    }
}

@Composable
private fun Frame(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = MaterialTheme.shapes.medium,
            ),
    ) {
        content()
    }
}

@Composable
private fun PageSection1(
    pageNumber: Int,
    userName: String,
    bodyHeight: Int,
    bodyWeight: Int,
    birthDate: String,
    gender: String,
) {
    Page(
        pageNumber = pageNumber,
        title = userName,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = "Informasi Personal",
                style = MaterialTheme.typography.titleMedium,
            )
            ProfileTextColumn(
                title = "Berat Badan",
                subtitle = "$bodyWeight kg",
            )
            ProfileTextColumn(
                title = "Tinggi Badan",
                subtitle = "$bodyHeight cm",
            )
            ProfileTextColumn(
                title = "Tanggal Lahir",
                subtitle = birthDate,
            )
            ProfileTextColumn(
                title = "Jenis Kelamin",
                subtitle = gender,
            )
        }
    }
}

@Composable
private fun PageSection2(
    pageNumber: Int,
    weeklyProgramProgress: Int,
) {
    Page(
        pageNumber = pageNumber,
        title = "Progres",
    ) {
        WeeklyProgramProgress(
            weeklyProgramProgress = weeklyProgramProgress,
            enableAnimation = false,
        )
    }
}

@Composable
private fun PageSection3(
    pageNumber: Int,
    preTestAsaqChartEntry: List<ChartEntry>,
    preTestAsaqChartXLabels: List<String>,
    preTestAsaqChartMaxY: Int,
    preTestAsaqChartYLabelCount: Int,
) {
    Page(
        pageNumber = pageNumber,
        title = "Sedenter Awal & Akhir",
    ) {
        PreTestAsaq(
            preTestAsaqChartEntry = preTestAsaqChartEntry,
            preTestAsaqChartXLabels = preTestAsaqChartXLabels,
            preTestAsaqChartMaxY = preTestAsaqChartMaxY,
            preTestAsaqChartYLabelCount = preTestAsaqChartYLabelCount,
            enableAnimation = false,
            chartHeightDp = 260,
        )
    }
}

@Composable
private fun PageSection4(
    pageNumber: Int,
    preTestAsaqChartEntry: List<ChartEntry>,
    postTestAsaqChartEntry: List<ChartEntry>,
    postTestAsaqChartXLabels: List<String>,
    postTestAsaqChartMaxY: Int,
    postTestAsaqChartYLabelCount: Int,
) {
    Page(
        pageNumber = pageNumber,
    ) {
        PostTestAsaq(
            preTestAsaqChartEntry = preTestAsaqChartEntry,
            postTestAsaqChartEntry = postTestAsaqChartEntry,
            postTestAsaqChartXLabels = postTestAsaqChartXLabels,
            postTestAsaqChartMaxY = postTestAsaqChartMaxY,
            postTestAsaqChartYLabelCount = postTestAsaqChartYLabelCount,
            enableAnimation = false,
            chartHeightDp = 260,
        )
    }
}

@Composable
private fun PageSection5(
    pageNumber: Int,
    weeklyAsaqChartEntry: List<ChartEntry>,
    weeklyAsaqChartXLabels: List<String>,
    weeklyAsaqChartMaxY: Int,
    weeklyAsaqChartYLabelCount: Int,
    weeklyAsaqSelectedWeek: Int,
    weeklyAsaqSelectedDayOfWeek: Int,
    showTitle: Boolean,
) {
    Page(
        pageNumber = pageNumber,
        title = if (showTitle) "Sedenter Harian" else null,
    ) {
        WeeklyAsaq(
            weeklyAsaqSelectedWeek = weeklyAsaqSelectedWeek,
            weeklyAsaqSelectedDayOfWeek = weeklyAsaqSelectedDayOfWeek,
            weeklyAsaqResponseChartEntry = weeklyAsaqChartEntry,
            weeklyAsaqResponseChartXLabels = weeklyAsaqChartXLabels,
            weeklyAsaqResponseChartMaxY = weeklyAsaqChartMaxY,
            weeklyAsaqResponseChartYLabelCount = weeklyAsaqChartYLabelCount,
            onSelectDayOfWeek = {},
            onSelectWeek = {},
            enableAnimation = false,
            chartHeightDp = 240,
        )
    }
}

@Composable
private fun PageSection6(
    pageNumber: Int,
    postTestFfqScore: Int,
    ffqScoreChartEntries: List<List<ChartEntry>>,
    ffqScoreChartXLabels: List<String>,
    ffqScoreChartMaxY: Int,
    ffqScoreChartYLabelCount: Int,
) {
    Page(
        pageNumber = pageNumber,
        title = "Frekuensi Makanan",
    ) {
        FfqScore(
            postTestFfqScore = postTestFfqScore,
            ffqScoreChartEntries = ffqScoreChartEntries,
            ffqScoreChartXLabels = ffqScoreChartXLabels,
            ffqScoreChartMaxY = ffqScoreChartMaxY,
            ffqScoreChartYLabelCount = ffqScoreChartYLabelCount,
            enableAnimation = false,
            chartHeightDp = 180,
        )
    }
}

@Composable
private fun PageSection7(
    pageNumber: Int,
    ffqCategoryChartEntry: List<ChartEntry>,
    ffqCategoryChartXLabels: List<String>,
    ffqCategoryChartYLabels: List<String>,
    ffqCategoryOptionsCategory: List<FfqFoodCategory>,
    ffqCategorySelectedCategory: Int,
    showTitle: Boolean,
) {
    Page(
        pageNumber = pageNumber,
        title = if (showTitle) "Frekuensi Kategori Makanan" else null
    ) {
        FfqCategory(
            ffqCategoryOptionsCategory = ffqCategoryOptionsCategory,
            ffqCategorySelectedCategory = ffqCategorySelectedCategory,
            ffqCategoryChartEntry = ffqCategoryChartEntry,
            ffqCategoryChartXLabels = ffqCategoryChartXLabels,
            ffqCategoryChartYLabels = ffqCategoryChartYLabels,
            onSelectCategory = {},
            enableAnimation = false,
            chartHeightDp = 220,
        )
    }
}

@Composable
private fun Page(
    pageNumber: Int,
    modifier: Modifier = Modifier,
    title: String? = null,
    pageRatio: Float = 0.7092199f, // A4 ratio
    content: @Composable () -> Unit,
) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = modifier
            .aspectRatio(pageRatio)
            .padding(24.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            if (title != null) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth(),
                )
                
                Spacer(modifier = Modifier.height(32.dp))
            }
            
            content()
        }
        
        Text(
            text = pageNumber.toString(),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}

private fun buildPdfDocument(
    bitmapList: List<ImageBitmap>,
): PdfDocument {
    val pdfDocument = PdfDocument()
    
    bitmapList.forEachIndexed { index, bitmap ->
        val pageInfo = PdfDocument.PageInfo.Builder(
            bitmap.width,
            bitmap.height,
            index + 1,
        ).create()
        val page = pdfDocument.startPage(pageInfo)
        page.canvas.drawBitmap(
            bitmap.asAndroidBitmap(),
            0f,
            0f,
            null,
        )
        pdfDocument.finishPage(page)
    }
    
    return pdfDocument
}

private fun saveToStorage(
    context: Context,
    pdfDocument: PdfDocument,
    fileName: String,
    directoryPathUri: Uri,
) {
    var parcelFileDescriptor: ParcelFileDescriptor? = null
    var fileOutputStream: FileOutputStream? = null
    
    try {
        val directory = DocumentFile.fromTreeUri(
            context,
            directoryPathUri,
        )
        val file = directory?.createFile(
            "application/pdf",
            fileName,
        )
        
        parcelFileDescriptor = context.contentResolver.openFileDescriptor(
            file!!.uri,
            "w",
        )
        
        fileOutputStream = FileOutputStream(parcelFileDescriptor?.fileDescriptor)
        pdfDocument.writeTo(fileOutputStream)
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        try {
            fileOutputStream?.close()
            parcelFileDescriptor?.close()
            pdfDocument.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}