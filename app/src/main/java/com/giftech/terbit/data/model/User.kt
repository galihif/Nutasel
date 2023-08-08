package com.giftech.terbit.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val nama:String = "",
    val tinggi:Int = 0,
    val berat:Int = 0,
    val usia:Int = 0,
    val isMale:Boolean = true,
    val skorIMT:Double = 0.0,
):Parcelable
