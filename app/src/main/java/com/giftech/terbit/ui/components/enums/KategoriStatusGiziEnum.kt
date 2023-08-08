package com.giftech.terbit.ui.components.enums

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import com.giftech.terbit.ui.theme.CustomColor1
import com.giftech.terbit.ui.theme.CustomColor3
import com.giftech.terbit.ui.theme.light_CustomColor2

enum class KategoriStatusGiziEnum(
    val title: String, val desc: String,
    val ambangBatas: String
) {
    GIZI_BURUK(
        title = "Gizi Buruk",
        ambangBatas = "< -3SD",
        desc = "Hasil perhitungan IMT kamu mendapatkan skor 22,9 berada dalam kategori Normal, hasil ini cukup baik, namun harus disesuaikan dengan kegiatan kamu sehari - hari, yuk coba kita lihat status gizi kamu!"
    ),
    GIZI_KURANG(
        title = "Gizi Kurang",
        ambangBatas = "-3SD sd -2SD",
        desc = "Hasil perhitungan IMT kamu mendapatkan skor 22,9 berada dalam kategori Normal, hasil ini cukup baik, namun harus disesuaikan dengan kegiatan kamu sehari - hari, yuk coba kita lihat status gizi kamu!"
    ),
    GIZI_BAIK(
        title = "Gizi Baik",
        ambangBatas = "-2SD sd +1SD",
        desc = "Hasil perhitungan IMT kamu mendapatkan skor 22,9 berada dalam kategori Normal, hasil ini cukup baik, namun harus disesuaikan dengan kegiatan kamu sehari - hari, yuk coba kita lihat status gizi kamu!",
    ),
    GIZI_LEBIH(
        title = "Gizi Lebih",
        ambangBatas = "+1SD sd +2SD",
        desc = "Hasil perhitungan IMT kamu mendapatkan skor 22,9 berada dalam kategori Normal, hasil ini cukup baik, namun harus disesuaikan dengan kegiatan kamu sehari - hari, yuk coba kita lihat status gizi kamu!"
    ),
    OBESITAS(
        title = "Obesitas",
        ambangBatas = "> +2SD",
        desc = "Hasil perhitungan IMT kamu mendapatkan skor 22,9 berada dalam kategori Normal, hasil ini cukup baik, namun harus disesuaikan dengan kegiatan kamu sehari - hari, yuk coba kita lihat status gizi kamu!"
    );

    val color: Color
        @Composable
        @ReadOnlyComposable
        get() = when (this) {
            GIZI_KURANG -> CustomColor1
            GIZI_BURUK -> MaterialTheme.colorScheme.inverseSurface
            GIZI_BAIK -> light_CustomColor2
            GIZI_LEBIH -> CustomColor3
            OBESITAS -> MaterialTheme.colorScheme.secondary
        }
}