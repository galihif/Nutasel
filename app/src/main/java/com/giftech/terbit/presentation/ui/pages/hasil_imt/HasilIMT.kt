package com.giftech.terbit.presentation.ui.pages.hasil_imt

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.giftech.terbit.presentation.ui.components.enums.HeroEnum
import com.giftech.terbit.presentation.ui.components.templates.Hasil
import com.giftech.terbit.presentation.util.toFormattedString

@Composable
fun HasilIMTScreen(
    onNext: () -> Unit = {},
    onBack: () -> Unit = {},
    viewModel: HasilIMTViewModel = hiltViewModel()
) {
    val user by remember { viewModel.user }.collectAsState()
    Hasil(
        onNext = {
            onNext()
        },
        onBack = onBack,
        hero = HeroEnum.HasilIMT,
        statusColor = user.kategoriIMT.color,
        nama = user.nama,
        skorTitle = "Skor IMT",
        skor = user.skorIMT.toFormattedString(),
        kategoriTitle = "Kategori IMT",
        kategori = user.kategoriIMT.title,
        desc = user.kategoriIMT.desc(user.skorIMT.toFormattedString()),
        buttonText = "Selanjutnya"
    )
}