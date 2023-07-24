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
    Asaq1(
        image = R.drawable.vector_onboard_asaq3,
        title = "Menonton TV",
        description = "Berapa lama waktu kamu menonton TV setiap harinya?"
    ),
    Asaq2(
        image = R.drawable.vector_onboard_asaq3,
        title = "Menonton Video / DVD",
        description = "Berapa lama waktu kamu menonton video / DVD setiap harinya? (dari device apapun)"
    ),
    Asaq3(
        image = R.drawable.vector_onboard_asaq3,
        title = "Menggunakan komputer untuk bersenang - senang",
        description = "Berapa lama waktu kamu menggunakan komputer untuk bersenang - senang?"
    ),
    Asaq4(
        image = R.drawable.vector_onboard_asaq3,
        title = "Menggunakan komputer / laptop untuk mengerjakan tugas",
        description = "Berapa lama waktu kamu menggunakan komputer / laptop untuk mengerjakan tugas?"
    ),
    Asaq5(
        image = R.drawable.vector_onboard_asaq3,
        title = "Mengerjakan tugas tidak di komputer / laptop",
        description = "Berapa lama waktu yang kamu habiskan untuk mengerjakan tugas dengan tidak menggunakan komputer / laptop?"
    ),
    Asaq6(
        image = R.drawable.vector_onboard_asaq3,
        title = "Membaca santai",
        description = "Berapa lama waktu yang kamu habiskan untuk membaca santai?"
    ),
    Asaq7(
        image = R.drawable.vector_onboard_asaq3,
        title = "Dibimbing untuk belajar",
        description = "Berapa lama waktu yang kamu habiskan ketika dibimbing untuk belajar?"
    ),
    Asaq8(
        image = R.drawable.vector_onboard_asaq3,
        title = "Bepergian menggunakan alat transportasi",
        description = "Berapa lama waktu yang kamu habiskan untuk berpergian menggunakan alat transportasi?"
    ),
    Asaq9(
        image = R.drawable.vector_onboard_asaq3,
        title = "Melakukan prakarya atau hobi",
        description = "Berapa lama waktu yang kamu habiskan untuk melakukan prakarya atau melakukan hobimu?"
    ),
    Asaq10(
        image = R.drawable.vector_onboard_asaq3,
        title = "Duduk santai (mengobrol dengan teman, melalui telepon, bersantai)",
        description = "Berapa lama waktu yang kamu habiskan untuk duduk santai mengobrol dengan teman, melalui telefon, atau bersantai?"
    ),
    Asaq11(
        image = R.drawable.vector_onboard_asaq3,
        title = "Bermain atau berlatih musik",
        description = "Berapa lama waktu yang kamu habiskan untuk bermain atau berlatih instrumen musik?"
    ),
    Asaq12(
        image = R.drawable.vector_onboard_asaq3,
        title = "Pergi ke tempat ibadah",
        description = "Berapa lama waktu yang kamu habiskan untuk pergi ke tempat ibadah? (sesuai agama dan kepercayaan masing - masing)"
    ),
}