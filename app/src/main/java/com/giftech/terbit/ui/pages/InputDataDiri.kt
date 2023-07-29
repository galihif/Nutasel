package com.giftech.terbit.ui.pages

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.giftech.terbit.ui.components.atoms.MyFilterChips
import com.giftech.terbit.ui.components.atoms.MyOutlinedTextField
import com.giftech.terbit.ui.components.atoms.PrimaryButton
import com.giftech.terbit.ui.components.enums.HeroEnum
import com.giftech.terbit.ui.components.molecules.HeroColumn

@ExperimentalMaterial3Api
@Composable
fun InputDataDiriScreen(
    onNext: () -> Unit
) {
    val scrollState = rememberScrollState()

    var nama by remember { mutableStateOf("") }
    var isMale by remember { mutableStateOf(true) }
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
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
                onValueChange = { nama = it },
                label = "Nama",
                supportingText = "Nama Lengkap Kamu",
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                MyOutlinedTextField(
                    value = nama,
                    onValueChange = { nama = it },
                    label = "Berat",
                    supportingText = "Kilogram (Kg)",
                    modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.width(24.dp))
                MyOutlinedTextField(
                    value = nama,
                    onValueChange = { nama = it },
                    label = "Tinggi",
                    supportingText = "Centimeter (Cm)",
                    modifier = Modifier.weight(1f)
                )
            }
            MyOutlinedTextField(
                value = nama,
                onValueChange = { nama = it },
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
                        isMale = it
                    },
                    text = "Laki-laki",
                )
                MyFilterChips(
                    selected = !isMale,
                    onSelectedChange = {
                        isMale = !it
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