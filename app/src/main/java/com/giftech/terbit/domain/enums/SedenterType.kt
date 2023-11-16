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
        recommendation = "Rata - rata waktu sedenter kamu minggu kemarin masih tergolong sedang, pertimbangkan untuk tetap mempertahankan dan terus jaga pola hidup sehat dengan terus beraktivitas setiap harinya jangan lupa diselingi olahraga minimal 30 menit sehari ya!",
    ),
    
    BERAT(
        title = "Berat",
        description = "Wah aktivitas sedenter kamu berat, yuk lakukan kegiatan di bawah ini agar pola hidupmu jauh lebih baik",
        recommendation = "Wah keren, rata - rata waktu sedenter kamu minggu kemarin termasuk rendah, artinya kamu lebih banyak bergerak daripada berdiam diri,  tetap luangkan waktu untung jeda kegiatan kamu dengan aktivitas fisik, minimal peregangan 3 menit disela aktivitas kamu!",
    ),
    
}