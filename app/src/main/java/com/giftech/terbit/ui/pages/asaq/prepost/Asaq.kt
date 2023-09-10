package com.giftech.terbit.ui.pages.asaq.prepost

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import com.giftech.terbit.domain.model.Asaq
import com.giftech.terbit.ui.components.atoms.MyFilterChips
import com.giftech.terbit.ui.components.atoms.MyOutlinedTextField
import com.giftech.terbit.ui.components.atoms.PrimaryButton
import com.giftech.terbit.ui.components.enums.HariEnum
import com.giftech.terbit.ui.components.molecules.HeroColumn
import com.giftech.terbit.ui.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AsaqScreen(
    viewModel: AsaqViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onNext: (score: Int) -> Unit
) {
    val currentAsaq by remember {
        viewModel.currentAsaq
    }.collectAsState()

    val currentQuestion by remember {
        viewModel.currentQuestion
    }.collectAsState()

    val currentNumber by remember {
        viewModel.currentNumber
    }.collectAsState()

    var jam by remember {
        mutableStateOf("")
    }
    var menit by remember {
        mutableStateOf("")
    }
    var durasiHariKerja by remember {
        mutableIntStateOf(0)
    }
    var durasiHariLibur by remember {
        mutableIntStateOf(0)
    }
    var selectedHari by remember {
        mutableStateOf(HariEnum.HARI_KERJA)
    }
    var modalSheetOpen by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(currentAsaq) {
        durasiHariKerja = currentAsaq.durasiHariKerja
        durasiHariLibur = currentAsaq.durasiHariLibur
    }
    LaunchedEffect(selectedHari, durasiHariKerja, durasiHariLibur) {
        when (selectedHari) {
            HariEnum.HARI_KERJA -> {
                jam = (durasiHariKerja / 60).toString()
                menit = (durasiHariKerja % 60).toString()
            }

            HariEnum.HARI_LIBUR -> {
                jam = (durasiHariLibur / 60).toString()
                menit = (durasiHariLibur % 60).toString()
            }

            else -> {}
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("${currentAsaq.questionId}/12")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        if (currentNumber > 1) {
                            viewModel.prevQuestion()
                        } else {
                            onBack()
                        }
                    }) {
                        Icon(Icons.Default.ArrowBack, "")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            HeroColumn(
                title = currentQuestion.title,
                description = currentQuestion.question,
                imageRes = currentQuestion.imageRes,
                imageHeight = 200
            )
            MyFilterChips(
                selected = durasiHariKerja != 0,
                onSelectedChange = {
                    selectedHari = HariEnum.HARI_KERJA
                    modalSheetOpen = true
                },
                text = HariEnum.HARI_KERJA.title,
                modifier = Modifier.fillMaxWidth()
            )
            MyFilterChips(
                selected = durasiHariLibur != 0,
                onSelectedChange = {
                    selectedHari = HariEnum.HARI_LIBUR
                    modalSheetOpen = true
                },
                text = HariEnum.HARI_LIBUR.title,
                modifier = Modifier.fillMaxWidth()
            )
            PrimaryButton(
                text = if (currentNumber < Constants.TOTAL_ASAQ) "Selanjutnya" else "Selesai",
                onClick = {
                    viewModel.nextQuestion(
                        Asaq(
                            questionId = currentAsaq.questionId,
                            durasiHariKerja = durasiHariKerja,
                            durasiHariLibur = durasiHariLibur,
                        )
                    )
                    if (currentNumber == Constants.TOTAL_ASAQ) {
                        viewModel.saveAsaq()
                        onNext(viewModel.totalScore.value)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = durasiHariKerja != 0 && durasiHariLibur != 0
            )
        }
        if (modalSheetOpen) {
            ModalBottomSheet(onDismissRequest = {
                modalSheetOpen = false
            }) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    MyOutlinedTextField(
                        //Jam
                        value = jam,
                        onValueChange = { newValue ->
                            if (newValue.isDigitsOnly()) {
                                jam = newValue
                            }
                        },
                        label = "Jam",
                        supportingText = if (jam.isNotEmpty() && jam.toInt() > 24) "Jam tidak boleh lebih dari 24" else "",
                    )
                    MyOutlinedTextField(
                        //Menit
                        value = menit,
                        onValueChange = { newValue ->
                            if (newValue.isDigitsOnly()) {
                                menit = newValue
                            }
                        },
                        label = "Menit",
                        supportingText = if (menit.isNotEmpty() && menit.toInt() > 60) "Menit tidak boleh lebih dari 60" else "",
                    )
                    PrimaryButton(
                        text = "Selanjutnya",
                        onClick = {
                            modalSheetOpen = false
                            when (selectedHari) {
                                HariEnum.HARI_KERJA -> {
                                    durasiHariKerja = jam.toInt() * 60 + menit.toInt()
                                }

                                HariEnum.HARI_LIBUR -> {
                                    durasiHariLibur = jam.toInt() * 60 + menit.toInt()
                                }

                                else -> {}
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = jam.isNotBlank() && menit.isNotBlank() && jam.toInt() <= 24 && menit.toInt() <= 60

                    )
                }
            }
        }
    }
}