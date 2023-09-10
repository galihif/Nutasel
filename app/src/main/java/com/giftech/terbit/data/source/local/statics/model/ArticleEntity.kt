package com.giftech.terbit.data.source.local.statics.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ArticleEntity(
    val articleId:Int,
    val week:Int,
    val day:Int,
    @StringRes val title:Int,
    @DrawableRes val imageRes:Int,
    val readDuration:Int,
    val category:List<String>,
    @StringRes val content:Int,
    val reference:String,
    val imageSource : String,
)
