package com.giftech.terbit.ui.components.enums

import com.giftech.terbit.R

enum class AsaqQuestions(
    val questionId: Int,
    val title: String,
    val question: String,
    val imageRes: Int
) {
    Asaq1(
        questionId = 1,
        title = "Menonton TV",
        question = "Berapa lama waktu kamu menonton TV setiap harinya?",
        imageRes = R.drawable.vector_onboard_asaq3
    ),
    Asaq2(
        questionId = 2,
        title = "Menonton Video / DVD",
        question = "Berapa lama waktu kamu menonton video / DVD setiap harinya? (dari device apapun)",
        imageRes = R.drawable.vector_onboard_asaq3
    ),
    Asaq3(
        questionId = 3,
        title = "Menggunakan komputer untuk bersenang-senang",
        question = "Berapa lama waktu kamu menggunakan komputer untuk bersenang-senang?",
        imageRes = R.drawable.vector_onboard_asaq3
    ),
    Asaq4(
        questionId = 4,
        title = "Menggunakan komputer / laptop untuk mengerjakan tugas",
        question = "Berapa lama waktu kamu menggunakan komputer / laptop untuk mengerjakan tugas?",
        imageRes = R.drawable.vector_onboard_asaq3
    ),
    Asaq5(
        questionId = 5,
        title = "Mengerjakan tugas tidak di komputer / laptop",
        question = "Berapa lama waktu yang kamu habiskan untuk mengerjakan tugas dengan tidak menggunakan komputer / laptop?",
        imageRes = R.drawable.vector_onboard_asaq3
    ),
    Asaq6(
        questionId = 6,
        title = "Membaca santai",
        question = "Berapa lama waktu yang kamu habiskan untuk membaca santai?",
        imageRes = R.drawable.vector_onboard_asaq3
    ),
    Asaq7(
        questionId = 7,
        title = "Dibimbing untuk belajar",
        question = "Berapa lama waktu yang kamu habiskan ketika dibimbing untuk belajar?",
        imageRes = R.drawable.vector_onboard_asaq3
    ),
    Asaq8(
        questionId = 8,
        title = "Bepergian menggunakan alat transportasi",
        question = "Berapa lama waktu yang kamu habiskan untuk berpergian menggunakan alat transportasi?",
        imageRes = R.drawable.vector_onboard_asaq3
    ),
    Asaq9(
        questionId = 9,
        title = "Melakukan prakarya atau hobi",
        question = "Berapa lama waktu yang kamu habiskan untuk melakukan prakarya atau melakukan hobimu?",
        imageRes = R.drawable.vector_onboard_asaq3
    ),
    Asaq10(
        questionId = 10,
        title = "Duduk santai (mengobrol dengan teman, melalui telepon, bersantai)",
        question = "Berapa lama waktu yang kamu habiskan untuk duduk santai mengobrol dengan teman, melalui telefon, atau bersantai?",
        imageRes = R.drawable.vector_onboard_asaq3
    ),
    Asaq11(
        questionId = 11,
        title = "Bermain atau berlatih musik",
        question = "Berapa lama waktu yang kamu habiskan untuk bermain atau berlatih instrumen musik?",
        imageRes = R.drawable.vector_onboard_asaq3
    ),
    Asaq12(
        questionId = 12,
        title = "Pergi ke tempat ibadah",
        question = "Berapa lama waktu yang kamu habiskan untuk pergi ke tempat ibadah? (sesuai agama dan kepercayaan masing-masing)",
        imageRes = R.drawable.vector_onboard_asaq3
    )
}
