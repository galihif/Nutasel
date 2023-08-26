package com.giftech.terbit.ui.pages.hasil_imt

import androidx.compose.runtime.Composable
import com.giftech.terbit.domain.model.User
import com.giftech.terbit.ui.components.enums.HeroEnum
import com.giftech.terbit.ui.components.templates.Hasil
import com.giftech.terbit.ui.utils.toFormattedString

@Composable
fun HasilIMTScreen(
    onNext: (User) -> Unit = {},
    onBack: () -> Unit = {},
    user: User,
) {
    Hasil(
        onNext = {
            onNext(user)
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