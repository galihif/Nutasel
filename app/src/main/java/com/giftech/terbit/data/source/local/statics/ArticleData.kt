package com.giftech.terbit.data.source.local.statics

import com.giftech.terbit.R
import com.giftech.terbit.data.source.local.statics.model.ArticleEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleData @Inject constructor() {
    fun getAll(): List<ArticleEntity> {
        val list = mutableListOf<ArticleEntity>()
        for (i in articleIdList.indices) {
            list.add(
                ArticleEntity(
                    articleId = articleIdList[i],
                    week = weekList[i],
                    day = dayList[i],
                    title = titleList[i],
                    imageRes = imageList[i],
                    readDuration = readDurationList[i],
                    category = categoryList[i],
                    content = contentList[i],
                    reference = referenceList[i],
                    imageSource = imageSourceList[i],
                    completeTitle = completeTitleList[i],
                    completeBody = completeBodyList[i]
                )
            )
        }
        return list
    }

    private val articleIdList = listOf(
        1,
        2,
        3,
        4,
        5,
        6,
        7,
        8,
    )

    private val weekList = listOf(
        1,
        2,
        3,
        4,
        1,
        2,
        3,
        4,
    )

    private val dayList = listOf(
        3,
        3,
        3,
        3,
        7,
        7,
        7,
        7,
    )

    private val titleList = listOf(
        R.string.title_week_1_3,
        R.string.title_week_2_3,
        R.string.title_week_3_3,
        R.string.title_week_4_3,
        R.string.title_week_1_7,
        R.string.title_week_2_7,
        R.string.title_week_3_7,
        R.string.title_week_4_7,
    )

    private val imageList = listOf(
        R.drawable.article_image_1_3,
        R.drawable.article_image_2_3,
        R.drawable.article_image_3_3,
        R.drawable.article_image_4_3,
        R.drawable.article_image_1_7,
        R.drawable.article_image_2_7,
        R.drawable.article_image_3_7,
        R.drawable.article_image_4_7,
    )

    private val readDurationList = listOf(
        4,
        3,
        5,
        3,
        2,
        2,
        4,
        4,
    )

    private val categoryList = listOf(
        listOf("Makanan Pokok", "Lauk Hewani", "Lauk Nabati"),
        listOf("Sayur", "Buah"),
        listOf("Selingan", "Minuman"),
        listOf("Frekuensi Makan", "Porsi"),
        listOf("Sedentary Lifestyle"),
        listOf("Sedentary Lifestyle"),
        listOf("Sedentary Lifestyle"),
        listOf("Sedentary Lifestyle"),
    )

    private val contentList = listOf(
        R.string.article_week_1_3,
        R.string.article_week_2_3,
        R.string.article_week_3_3,
        R.string.article_week_4_3,
        R.string.article_week_1_7,
        R.string.article_week_2_7,
        R.string.article_week_3_7,
        R.string.article_week_4_7,
    )

    private val referenceList = listOf(
        "Direktorat Jenderal Bina Gizi dan Kesehatan Ibu dan Anak. (2014). Pedoman Gizi Seimbang. Kementerian Kesehatan Republik Indonesia.",
        "Direktorat Jenderal Bina Gizi dan Kesehatan Ibu dan Anak. (2014). Pedoman Gizi Seimbang. Kementerian Kesehatan Republik Indonesia.",
        "Direktorat Jenderal Bina Gizi dan Kesehatan Ibu dan Anak. (2014). Pedoman Gizi Seimbang. Kementerian Kesehatan Republik Indonesia.",
        "Direktorat Jenderal Bina Gizi dan Kesehatan Ibu dan Anak. (2014). Pedoman Gizi Seimbang. Kementerian Kesehatan Republik Indonesia.",
        "Benarkah Sedentary Lifestyle Punya Dampak Buruk Bagi Kesehatan? (2022). SDGs Youth Hub. August 26, 2023, https://sdgsyouthhub.id/berita-blog/blog/benarkah-sedentary-lifestyle-punya-dampak-buruk-bagi-kesehatan/",
        "Benarkah Sedentary Lifestyle Punya Dampak Buruk Bagi Kesehatan? (2022). SDGs Youth Hub. August 26, 2023, https://sdgsyouthhub.id/berita-blog/blog/benarkah-sedentary-lifestyle-punya-dampak-buruk-bagi-kesehatan/",
        "Benarkah Sedentary Lifestyle Punya Dampak Buruk Bagi Kesehatan? (2022). SDGs Youth Hub. August 26, 2023, https://sdgsyouthhub.id/berita-blog/blog/benarkah-sedentary-lifestyle-punya-dampak-buruk-bagi-kesehatan/",
        "Benarkah Sedentary Lifestyle Punya Dampak Buruk Bagi Kesehatan? (2022). SDGs Youth Hub. August 26, 2023, https://sdgsyouthhub.id/berita-blog/blog/benarkah-sedentary-lifestyle-punya-dampak-buruk-bagi-kesehatan/",
    )

    private val imageSourceList = listOf(
        "Unsplash.com",
        "Unsplash.com",
        "Unsplash.com",
        "Kementrian Kesehatan RI",
        "Unsplash.com",
        "Unsplash.com",
        "Unsplash.com",
        "Unsplash.com",
    )
    private val completeTitleList = listOf(
        "Ilmu adalah kunci!",
        "Semakin tahu!",
        "Pengetahuan tak terbatas!",
        "Inspirasi sekitar!",
        "Ilmu adalah kunci!",
        "Semakin tahu!",
        "Pengetahuan tak terbatas!",
        "Inspirasi sekitar!",
    )
    private val completeBodyList = listOf(
        "Selamat kamu telah selesai membaca artikel, jangan lupa, membaca adalah tanda bahwa kamu peduli dengan perkembangan dirimu sendiri!",
        "Kamu telah selesai membaca  artikel minggu kedua, informasi adalah sesuatu yang berharga, terus semangat membaca!",
        "Kamu telah selesai membaca artikel minggu ketiga, setiap kata yang kamu baca adalah langkah menuju impianmu!",
        "Selamat, kamu telah menyelesaikan program membaca artikel, kamu sangat menginspirasi sekitar, jangan stop di sini, teruslah membaca dan teruslah berkembang!",
        "Selamat kamu telah selesai membaca artikel, jangan lupa, membaca adalah tanda bahwa kamu peduli dengan perkembangan dirimu sendiri!",
        "Kamu telah selesai membaca  artikel minggu kedua, informasi adalah sesuatu yang berharga, terus semangat membaca!",
        "Kamu telah selesai membaca artikel minggu ketiga, setiap kata yang kamu baca adalah langkah menuju impianmu!",
        "Selamat, kamu telah menyelesaikan program membaca artikel, kamu sangat menginspirasi sekitar, jangan stop di sini, teruslah membaca dan teruslah berkembang!",
    )

}