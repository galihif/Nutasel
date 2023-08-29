package com.giftech.terbit.ui.pages.input_data_diri

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import com.giftech.terbit.ui.components.atoms.MyFilterChips
import com.giftech.terbit.ui.components.atoms.MyOutlinedTextField
import com.giftech.terbit.ui.components.atoms.PrimaryButton
import com.giftech.terbit.ui.components.enums.HeroEnum
import com.giftech.terbit.ui.components.molecules.HeroColumn
import com.giftech.terbit.ui.components.molecules.MyDatePickerDialog

@ExperimentalMaterial3Api
@Composable
fun InputDataDiriScreen(
    onNext: () -> Unit,
    viewModel: InputDataDiriViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()

    val nama by remember { viewModel.nama }.collectAsState()
    val tinggi by remember { viewModel.tinggi }.collectAsState()
    val berat by remember { viewModel.berat }.collectAsState()
    val tglLahir by remember { viewModel.tglLahir }.collectAsState()
    val isMale by remember { viewModel.isMale }.collectAsState()
    var isValid by remember { mutableStateOf(false) }

    var showDatePicker by remember {
        mutableStateOf(false)
    }
    if (showDatePicker) {
        MyDatePickerDialog(
            onDateSelected = { viewModel.tglLahir.value = it },
            onDismiss = { showDatePicker = false }
        )
    }
    LaunchedEffect(nama, tinggi, berat, tglLahir) {
        isValid =
            nama.isNotBlank() && tinggi.isNotBlank() && berat.isNotBlank() && tglLahir.isNotBlank()
    }
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HeroColumn(
                title = "Data Diri",
                description = "Isi data diri kamu dengan benar",
                imageRes = HeroEnum.InputDataDiri.image,
            )
            Text(
                text = "Informasi Personal",
                style = MaterialTheme.typography.titleMedium
            )
            MyOutlinedTextField(
                value = nama,
                onValueChange = { viewModel.nama.value = it },
                label = "Nama",
                supportingText = "Nama Lengkap Kamu",
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                MyOutlinedTextField(
                    value = berat,
                    onValueChange = {
                        if (it.isDigitsOnly()) {
                            viewModel.berat.value = it
                        }
                    },
                    label = "Berat",
                    supportingText = "Kilogram (Kg)",
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )
                Spacer(Modifier.width(24.dp))
                MyOutlinedTextField(
                    value = tinggi,
                    onValueChange = {
                        if (it.isDigitsOnly()) {
                            viewModel.tinggi.value = it
                        }
                    },
                    label = "Tinggi",
                    supportingText = "Centimeter (Cm)",
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )
            }
            MyOutlinedTextField(
                value = tglLahir,
                onValueChange = {},
                readOnly = true,
                label = "Tanggal Lahir",
                supportingText = "Tanggal/Bulan/Tahun",
                trailingIcon = {
                    IconButton(onClick = {
                        showDatePicker = true
                    }) {
                        Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = "")
                    }
                }
            )
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = "Jenis Kelamin",
                    style = MaterialTheme.typography.titleMedium
                )
                MyFilterChips(
                    selected = isMale,
                    onSelectedChange = {
                        viewModel.isMale.value = it
                    },
                    text = "Laki-laki",
                )
                MyFilterChips(
                    selected = !isMale,
                    onSelectedChange = {
                        viewModel.isMale.value = !it
                    },
                    text = "Perempuan",
                )
            }
            PrimaryButton(
                text = "Selanjutnya",
                onClick = {
                    onNext()
                    viewModel.saveUser()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = isValid
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewInputDataDiriScreen() {
    InputDataDiriScreen(
        onNext = {}
    )
}