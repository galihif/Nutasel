package com.giftech.terbit.ui.components.enums

import androidx.compose.ui.graphics.Color
import com.giftech.terbit.ui.theme.dark_onCustomColor1
import com.giftech.terbit.ui.theme.light_CustomColor1
import com.giftech.terbit.ui.theme.light_CustomColor2
import com.giftech.terbit.ui.theme.light_CustomColor3
import com.giftech.terbit.ui.theme.md_theme_light_secondary

enum class KategoriIMTEnum(val title:String, val color:Color, val desc:String) {
    NORMAL(
        title = "Normal",
        color = light_CustomColor2,
        desc = "Hasil perhitungan IMT kamu mendapatkan skor 22,9 berada dalam kategori Normal, hasil ini cukup baik, namun harus disesuaikan dengan kegiatan kamu sehari - hari, yuk coba kita lihat status gizi kamu!"
    ),
    KURUS(
        title = "Kurus",
        color = light_CustomColor1,
        desc = "Hasil perhitungan IMT kamu mendapatkan skor 22,9 berada dalam kategori Normal, hasil ini cukup baik, namun harus disesuaikan dengan kegiatan kamu sehari - hari, yuk coba kita lihat status gizi kamu!"
    ),
    SANGAT_KURUS(
        title = "Sangat Kurus",
        color = dark_onCustomColor1,
        desc = "Hasil perhitungan IMT kamu mendapatkan skor 22,9 berada dalam kategori Normal, hasil ini cukup baik, namun harus disesuaikan dengan kegiatan kamu sehari - hari, yuk coba kita lihat status gizi kamu!"
    ),
    GEMUK(
        title = "Gemuk",
        color = light_CustomColor3,
        desc = "Hasil perhitungan IMT kamu mendapatkan skor 22,9 berada dalam kategori Normal, hasil ini cukup baik, namun harus disesuaikan dengan kegiatan kamu sehari - hari, yuk coba kita lihat status gizi kamu!"
    ),
    OBESITAS(
        title = "Obesitas",
        color = md_theme_light_secondary,
        desc = "Hasil perhitungan IMT kamu mendapatkan skor 22,9 berada dalam kategori Normal, hasil ini cukup baik, namun harus disesuaikan dengan kegiatan kamu sehari - hari, yuk coba kita lihat status gizi kamu!"
    ),
}