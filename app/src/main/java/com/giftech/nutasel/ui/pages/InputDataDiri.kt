package com.giftech.nutasel.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.giftech.nutasel.R
import com.giftech.nutasel.ui.components.molecules.HeroColumn

@ExperimentalMaterial3Api
@Composable
fun InputDataDiriScreen() {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            HeroColumn(
                image = R.drawable.vector_datadiri,
                title = "Isi data diri dulu ya",
                description = "Selamat datang di kolom isi data diri kamu, pastikan untuk mengisi semua kolom dengan informasi yang akurat ya!"
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewInputDataDiriScreen() {
    InputDataDiriScreen()
}