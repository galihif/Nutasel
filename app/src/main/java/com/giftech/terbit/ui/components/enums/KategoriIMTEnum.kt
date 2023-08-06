package com.giftech.terbit.ui.components.enums

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import com.giftech.terbit.ui.theme.CustomColor1
import com.giftech.terbit.ui.theme.CustomColor3
import com.giftech.terbit.ui.theme.light_CustomColor2

enum class KategoriIMTEnum(val title:String, val desc:String) {
    NORMAL(
        title = "Normal",
        desc = "Hasil perhitungan IMT kamu mendapatkan skor 22,9 berada dalam kategori Normal, hasil ini cukup baik, namun harus disesuaikan dengan kegiatan kamu sehari - hari, yuk coba kita lihat status gizi kamu!"
    ),
    KURUS(
        title = "Kurus",
        desc = "Hasil perhitungan IMT kamu mendapatkan skor 22,9 berada dalam kategori Normal, hasil ini cukup baik, namun harus disesuaikan dengan kegiatan kamu sehari - hari, yuk coba kita lihat status gizi kamu!"
    ),
    SANGAT_KURUS(
        title = "Sangat Kurus",
        desc = "Hasil perhitungan IMT kamu mendapatkan skor 22,9 berada dalam kategori Normal, hasil ini cukup baik, namun harus disesuaikan dengan kegiatan kamu sehari - hari, yuk coba kita lihat status gizi kamu!"
    ),
    GEMUK(
        title = "Gemuk",
        desc = "Hasil perhitungan IMT kamu mendapatkan skor 22,9 berada dalam kategori Normal, hasil ini cukup baik, namun harus disesuaikan dengan kegiatan kamu sehari - hari, yuk coba kita lihat status gizi kamu!"
    ),
    OBESITAS(
        title = "Obesitas",

        desc = "Hasil perhitungan IMT kamu mendapatkan skor 22,9 berada dalam kategori Normal, hasil ini cukup baik, namun harus disesuaikan dengan kegiatan kamu sehari - hari, yuk coba kita lihat status gizi kamu!"
    );
    val color: Color
        @Composable
        @ReadOnlyComposable
        get() = when(this) {
            NORMAL -> light_CustomColor2
            KURUS -> CustomColor1
            SANGAT_KURUS -> MaterialTheme.colorScheme.inverseSurface
            GEMUK -> CustomColor3
            OBESITAS -> MaterialTheme.colorScheme.secondary
        }
}