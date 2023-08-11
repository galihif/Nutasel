package com.giftech.terbit.ui.pages.hasil_status_gizi

import androidx.compose.runtime.Composable
import com.giftech.terbit.data.model.User
import com.giftech.terbit.ui.components.enums.HeroEnum
import com.giftech.terbit.ui.components.templates.Hasil

@Composable
fun HasilStatusGiziScreen(
    onNext: () -> Unit = {},
    onBack: () -> Unit = {},
    user: User,
) {
    Hasil(
        onNext = onNext,
        onBack = onBack,
        hero = HeroEnum.HasilStatusGizi,
        statusColor = user.kategoriStatusGizi.color,
        nama = user.nama,
        skorTitle = "Ambang Batas",
        skor = user.kategoriStatusGizi.ambangBatas,
        kategoriTitle = "KategoriMT",
        kategori = user.kategoriStatusGizi.title,
        desc = user.kategoriStatusGizi.desc,
        buttonText = "Selanjutnya"
    )
}