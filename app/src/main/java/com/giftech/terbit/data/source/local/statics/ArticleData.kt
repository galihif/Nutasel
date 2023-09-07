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
        "Love-Hate Relationship : Makanan Pokok dan Lauknya",
        "Get the Glow Up : Sayur dan Buah",
        "Make It Simple : Selingan dan Minuman Jadi",
        "What’s on My Plate? : Frekuensi Makan dan Batasan Porsinya",
        "Sedentary Lifestyle : Kenalan dengan Kegiatannya",
        "Sedentary Lifestyle : Durasi Kegiatannya",
        "Sedentary Lifestyle : Potensi Risikonya pada Kesehatan",
        "Sedentary Lifestyle : Tips Mengatasi Kebiasaannya"
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
        R.string.article_week_1_3,
        R.string.article_week_1_3,
        R.string.article_week_1_3,
        R.string.article_week_1_3,
        R.string.article_week_1_3,
        R.string.article_week_1_3,
        R.string.article_week_1_3,
    )

    private val referenceList = listOf(
        "Direktorat Jenderal Bina Gizi dan Kesehatan Ibu dan Anak. (2014). Pedoman Gizi Seimbang. Kementerian Kesehatan Republik Indonesia.",
        "Direktorat Jenderal Bina Gizi dan Kesehatan Ibu dan Anak. (2014). Pedoman Gizi Seimbang. Kementerian Kesehatan Republik Indonesia.",
        "Direktorat Jenderal Bina Gizi dan Kesehatan Ibu dan Anak. (2014). Pedoman Gizi Seimbang. Kementerian Kesehatan Republik Indonesia.",
        "Direktorat Jenderal Bina Gizi dan Kesehatan Ibu dan Anak. (2014). Pedoman Gizi Seimbang. Kementerian Kesehatan Republik Indonesia.",
        "Direktorat Jenderal Bina Gizi dan Kesehatan Ibu dan Anak. (2014). Pedoman Gizi Seimbang. Kementerian Kesehatan Republik Indonesia.",
        "Direktorat Jenderal Bina Gizi dan Kesehatan Ibu dan Anak. (2014). Pedoman Gizi Seimbang. Kementerian Kesehatan Republik Indonesia.",
        "Direktorat Jenderal Bina Gizi dan Kesehatan Ibu dan Anak. (2014). Pedoman Gizi Seimbang. Kementerian Kesehatan Republik Indonesia.",
        "Direktorat Jenderal Bina Gizi dan Kesehatan Ibu dan Anak. (2014). Pedoman Gizi Seimbang. Kementerian Kesehatan Republik Indonesia.",
    )

    private val imageSourceList = listOf(
        "Unsplash.com",
        "Unsplash.com",
        "",
        "",
        "",
        "",
        "",
        "",
    )
}