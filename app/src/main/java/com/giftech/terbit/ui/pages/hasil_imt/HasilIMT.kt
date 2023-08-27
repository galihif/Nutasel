package com.giftech.terbit.ui.pages.hasil_imt

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.giftech.terbit.ui.components.enums.HeroEnum
import com.giftech.terbit.ui.components.templates.Hasil
import com.giftech.terbit.ui.utils.toFormattedString

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
        kategoriTitle = "KategoriMT",
        kategori = user.kategoriIMT.title,
        desc = user.kategoriIMT.desc(user.skorIMT.toFormattedString()),
        buttonText = "Selanjutnya"
    )
}