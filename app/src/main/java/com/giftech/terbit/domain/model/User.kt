package com.giftech.terbit.domain.model

import android.os.Parcelable
import com.giftech.terbit.presentation.ui.components.enums.KategoriIMTEnum

import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kotlin.math.pow

@Parcelize
data class User(
    val nama:String = "",
    val tinggi:Int = 170,
    val berat:Int = 70,
    val tglLahir:String = "",
    val isMale:Boolean = true,
):Parcelable{
    @IgnoredOnParcel
    var tinggiInM = tinggi.toDouble()/100
    @IgnoredOnParcel
    var skorIMT = berat.toDouble() / (tinggiInM.pow(2))

    @IgnoredOnParcel
    var kategoriIMT = when {
        skorIMT < 17.0 -> KategoriIMTEnum.SANGAT_KURUS
        skorIMT < 18.5 -> KategoriIMTEnum.KURUS
        skorIMT < 25 -> KategoriIMTEnum.NORMAL
        skorIMT < 27 -> KategoriIMTEnum.GEMUK
        else -> KategoriIMTEnum.OBESITAS
    }
}