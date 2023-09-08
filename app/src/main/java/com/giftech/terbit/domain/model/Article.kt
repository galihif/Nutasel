package com.giftech.terbit.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Article(
    val articleId:Int,
    val week:Int,
    val day:Int,
    val title:String,
    val imageRes:Int,
    val readDuration:Int,
    val category:List<String>,
    val content:Int,
    val reference:String,
    val imageSource : String,
)