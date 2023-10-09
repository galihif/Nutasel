package com.giftech.terbit.presentation.ui.pages.asaq.prepost

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.giftech.terbit.domain.model.Asaq
import com.giftech.terbit.presentation.ui.components.atoms.MyFilterChips
import com.giftech.terbit.presentation.ui.components.atoms.MyOutlinedTextField
import com.giftech.terbit.presentation.ui.components.atoms.PrimaryButton
import com.giftech.terbit.presentation.ui.components.enums.HariEnum
import com.giftech.terbit.presentation.ui.components.molecules.HeroColumn
import com.giftech.terbit.presentation.ui.route.Screen
import com.giftech.terbit.presentation.util.Constants
import com.giftech.terbit.domain.util.Constants as DomainConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AsaqScreen(
    viewModel: AsaqViewModel = hiltViewModel(),
    isPreTest: Boolean,
    programId: Int,
    navController: NavController,
) {
    LaunchedEffect(isPreTest, programId) {
        viewModel.isPreTest = isPreTest
        viewModel.programId = programId
    }
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
        mutableIntStateOf(-1)
    }
    var durasiHariLibur by remember {
        mutableIntStateOf(-1)
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
        val durasiJamKerja = if (durasiHariKerja < 0) -1 else durasiHariKerja / 60
        val durasiMenitKerja = if (durasiHariKerja < 0) -1 else durasiHariKerja % 60
        val durasiJamLibur = if (durasiHariLibur < 0) -1 else (durasiHariLibur / 60)
        val durasiMenitLibur = if (durasiHariLibur < 0) -1 else (durasiHariLibur % 60)
        Log.d(
            "GALIH",
            "durasiJamKerja: $durasiJamKerja durasiMenitKerja: $durasiMenitKerja durasiJamLibur: $durasiJamLibur durasiMenitLibur: $durasiMenitLibur"
        )
        when (selectedHari) {
            HariEnum.HARI_KERJA -> {
                jam = if (durasiJamKerja < 0) "" else durasiJamKerja.toString()
                menit = if (durasiMenitKerja < 0) "" else durasiMenitKerja.toString()
            }

            HariEnum.HARI_LIBUR -> {
                jam = if (durasiJamLibur < 0) "" else durasiJamLibur.toString()
                menit = if (durasiMenitLibur < 0) "" else durasiMenitLibur.toString()
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
                            navController.popBackStack()
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
                selected = durasiHariKerja >= 0,
                onSelectedChange = {
                    selectedHari = HariEnum.HARI_KERJA
                    modalSheetOpen = true
                },
                text = HariEnum.HARI_KERJA.title,
                modifier = Modifier.fillMaxWidth()
            )
            MyFilterChips(
                selected = durasiHariLibur >= 0,
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
                        if (isPreTest) {
                            navController.navigate(Screen.FfqOnboarding.route)
                        } else {
                            navController.navigate(
                                Screen.FfqMain.createRoute(
                                    programId = DomainConstants.ProgramId.LAST_FFQ,
                                )
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = durasiHariKerja >= 0 && durasiHariLibur >= 0
            )
        }
        if (modalSheetOpen) {
            ModalBottomSheet(onDismissRequest = {
                modalSheetOpen = false
            }) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 64.dp),
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
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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