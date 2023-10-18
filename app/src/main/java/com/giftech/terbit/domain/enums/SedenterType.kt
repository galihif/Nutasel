package com.giftech.terbit.domain.enums

enum class SedenterType(
    val title:String,
    val description:String,
) {
    RINGAN("Ringan", "Aktivitas sedenter kamu ringan nih, yuk kita perbaiki lagi agar pola hidup kamu menjadi lebih baik."),
    SEDANG("Sedang", "Aktivitas sedenter dan pola makanmu sepertinya kurang baik, yuk kita perbaiki dengan aktivitas ini ya"),
    BERAT("Berat", "Wah aktivitas sedenter kamu berat, yuk lakukan kegiatan di bawah ini agar pola hidupmu jauh lebih baik"),
}