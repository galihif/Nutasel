package com.giftech.nutasel.ui.components.enums

import androidx.annotation.DrawableRes
import com.giftech.nutasel.R

enum class HeroEnum(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
) {
    InputDataDiri(
        image = R.drawable.vector_datadiri,
        title = "Isi data diri dulu ya",
        description = "Selamat datang di kolom isi data diri kamu, pastikan untuk mengisi semua kolom dengan informasi yang akurat ya!"
    ),
    AsaqOnboard1(
        image = R.drawable.vector_onboard_asaq1,
        title = "Tingkat Aktivitas",
        description = "Selamat datang di tahap selanjutnya, kami ingin memahami banyak tentang aktivitas kamu, selanjutnya kami telah menyiapakan beberapa pertanyaan singkat mengenai tingkat aktivitas sehari - harimu, yuk kita mulai!"
    ),
    AsaqOnboard2(
        image = R.drawable.vector_onboard_asaq2,
        title = "Panduan Pengisian",
        description = "Selanjutnya kami akan memberikan pertanyaan per kategori, akan diberikan pertanyaan kegiatan dan disediakan jawaban mulai dari hari senin hingga minggu per pertanyaan."
    ),
    AsaqOnboard3(
        image = R.drawable.vector_onboard_asaq3,
        title = "Pengisian Kuesioner",
        description = "Sebelum mengisi mohon luangkan waktu sekitar 15 menit untuk menjawab pertanyaan berikut ya!"
    ),
}