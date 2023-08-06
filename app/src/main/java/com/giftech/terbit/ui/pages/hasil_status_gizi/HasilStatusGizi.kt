package com.giftech.terbit.ui.pages.hasil_status_gizi

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

@Composable
fun HasilStatusGiziScreen(
    onNext: () -> Unit = {},
    onBack: () -> Unit = {},
    user: User,
    viewModel: HasilStatusGiziViewModel = hiltViewModel()
) {
    LaunchedEffect(user) {
        viewModel.setUser(user)
    }
    val skorStatusGizi by remember{
        viewModel.skorStatusGizi
    }.collectAsState()

    val kategoriStatusGizi by remember {
        viewModel.kategoriStatusGizi
    }.collectAsState()

    key(skorStatusGizi, kategoriStatusGizi) {
        Hasil(
            onNext = onNext,
            onBack = onBack,
            hero = HeroEnum.HasilStatusGizi,
            statusColor = kategoriStatusGizi.color,
            nama = user.nama,
            skorTitle = "Ambang Batas",
            skor = kategoriStatusGizi.ambangBatas,
            kategoriTitle = "KategoriMT",
            kategori = kategoriStatusGizi.title,
            desc = kategoriStatusGizi.desc,
            buttonText = "Selanjutnya"
        )
    }
}