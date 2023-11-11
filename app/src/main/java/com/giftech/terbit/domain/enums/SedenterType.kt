package com.giftech.terbit.domain.enums

enum class SedenterType(
    val title: String,
    val description: String,
    val recommendation: String,
) {
    
    RINGAN(
        title = "Ringan",
        description = "Aktivitas sedenter kamu ringan nih, yuk kita perbaiki lagi agar pola hidup kamu menjadi lebih baik.",
        recommendation = "Wah keren, rata - rata waktu sedenter kamu minggu kemarin termasuk rendah, artinya kamu lebih banyak bergerak daripada berdiam diri, ini merupakan tanda positif untuk kesehatan fisik kamu, terus pertahankan ya!",
    ),
    
    SEDANG(
        title = "Sedang",
        description = "Aktivitas sedenter dan pola makanmu sepertinya kurang baik, yuk kita perbaiki dengan aktivitas ini ya",
        recommendation = "Rata - rata waktu sedenter kamu minggu kemarin masih tergolong sedang, yang menunjukan tingkat aktivitas yang cukup seimbang, pertimbangkan untuk tetap mempertahankan dan terus jaga pola hidup sehat dengan terus beraktivitas setiap harinya.",
    ),
    
    BERAT(
        title = "Berat",
        description = "Wah aktivitas sedenter kamu berat, yuk lakukan kegiatan di bawah ini agar pola hidupmu jauh lebih baik",
        recommendation = "Rata - rata kamu tergolong berat, kalau dibiasakan bisa berdampak buruk buat kesehatan, kami sarankan buat lebih meningkatkan aktivitas fisiknya ya dan jangan lupa untuk melakukan aktivitas olahraga yang kamu suka seperti jogging, jalan sehat dan lainnya selama minimal 30 menit sehari ya!",
    ),
    
}