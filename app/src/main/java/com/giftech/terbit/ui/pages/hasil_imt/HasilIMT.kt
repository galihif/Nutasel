package com.giftech.terbit.ui.pages.hasil_imt

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.giftech.terbit.data.model.User
import com.giftech.terbit.ui.components.enums.HeroEnum
import com.giftech.terbit.ui.components.templates.Hasil
import com.giftech.terbit.ui.utils.toFormattedString

@Composable
fun HasilIMTScreen(
    onNext: (User) -> Unit = {},
    onBack: () -> Unit = {},
    user: User,
    viewModel: HasilIMTViewModel = hiltViewModel()
) {
    LaunchedEffect(user) {
        viewModel.setUser(user)
    }
    val skorIMT by remember{
        viewModel.skorIMT
    }.collectAsState()

    val kategoriIMT by remember {
        viewModel.kategoriIMT
    }.collectAsState()

    key(skorIMT, kategoriIMT) {
        Hasil(
            onNext = {
                onNext(user.copy(skorIMT = skorIMT))
            },
            onBack = onBack,
            hero = HeroEnum.HasilIMT,
            statusColor = kategoriIMT.color,
            nama = user.nama,
            skorTitle = "Skor IMT",
            skor = skorIMT.toFormattedString(),
            kategoriTitle = "KategoriMT",
            kategori = kategoriIMT.title,
            desc = kategoriIMT.desc,
            buttonText = "Cek Status Gizi"
        )
    }
}