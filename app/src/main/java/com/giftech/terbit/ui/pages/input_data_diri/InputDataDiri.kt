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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import com.giftech.terbit.ui.components.atoms.MyFilterChips
import com.giftech.terbit.ui.components.atoms.MyOutlinedTextField
import com.giftech.terbit.ui.components.atoms.PrimaryButton
import com.giftech.terbit.ui.components.enums.HeroEnum
import com.giftech.terbit.ui.components.molecules.HeroColumn

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
                hero = HeroEnum.InputDataDiri
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
                    modifier = Modifier.weight(1f)
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
                    modifier = Modifier.weight(1f)
                )
            }
            MyOutlinedTextField(
                value = tglLahir,
                onValueChange = { viewModel.tglLahir.value = it },
                label = "Tanggal Lahir",
                supportingText = "Bulan, Tanggal, Tahun",
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
                onClick = onNext,
                modifier = Modifier.fillMaxWidth()
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