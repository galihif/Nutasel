package com.giftech.terbit.ui.components.enums

import androidx.annotation.DrawableRes
import com.giftech.terbit.R

enum class HeroEnum(
    @DrawableRes val image: Int,
    val title: String,
    val description: String,
) {
    InputDataDiri(
        image = R.drawable.vector_datadiri,
        title = "Isi data diri dulu ya",
        description = "Selamat datang di kolom isi data diri kamu, pastikan untuk mengisi semua kolom dengan informasi yang akurat ya!"
    ),
    LoadingIMT(
        image = R.drawable.vector_hasil_imt,
        title = "Mengkalkulasikan IMT Kamu",
        description = ""
    ),
    HasilIMT(
        image = R.drawable.vector_hasil_imt,
        title = "Indeks Massa Tubuh",
        description = "Yuk lihat hasil dan kategori dari perhitungan IMT kamu!"
    ),
    LoadingStatusGizi(
        image = R.drawable.vector_status_gizi,
        title = "Mengkalkulasikan hasil status gizi Kamu",
        description = ""
    ),
    HasilStatusGizi(
        image = R.drawable.vector_status_gizi,
        title = "Status Gizi",
        description = "Yuk lihat hasil status gizi kamu!"
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
    FfqOnboard1(
        image = R.drawable.vector_onboard_ffq1,
        title = "Frekuensi Makanan",
        description = "Selanjutnya, kamu akan diberikan pilihan untuk menginput makanan yang kamu makan dalam sebulan terakhir, ini untuk mengetahui perkembanganmu sebelum dan sesudah menggunakan aplikasi Terbit!",
    ),
    LoadingHasilTP(
        image = R.drawable.vector_onboard_tp,
        title = "Mengkalkulasikan tingkat pemantauan kamu",
        description = ""
    ),
    ActComplete1(
        image = R.drawable.vector_act_complete_1,
        title = "Awalan yang bagus!",
        description = "Kamu keren banget udah menyelesaikan task minggu pertama, terus semangat dan konsisten kamu pasti bisa!"
    ),
    ActComplete2(
        image = R.drawable.vector_act_complete_2,
        title = "Mendekati Puncak!",
        description = "Konsistenmu untuk menyelesaikan program minggu ke dua luar biasa, kamu semakin mendekati puncak tujuanmu, terus pertahankan!"
    ),
    ActComplete3(
        image = R.drawable.vector_act_complete_3,
        title = "Tidak Tergoyahkan!",
        description = "Kamu adalah bukti nyata bahwa terus fokus dan konsisten akan membawakan hasil, tetap fokus dan tak ada yang bisa menghalangimu meraih tujuan!"
    ),
    ActComplete4(
        image = R.drawable.vector_act_complete_4,
        title = "Pencapaian Besar!",
        description = "Hebat sekali, kamu telah melewati banyak tantangan dan mencapai target akhir, jadikan pencapaian ini sebagai momentum untuk meraih hal besar lainnya!"
    ),
    OnboardPosttest(
        image = R.drawable.vector_onboard_posttest,
        title = "Saatnya Post Test",
        description = "Mantap, kamu sudah konsisten menekuni program yang telah disajikan, selannjutnya mari kita lihat sejauh mana kamu menerapkan pola hidup yang sudah kamu latih sebelumnya"
    ),
}