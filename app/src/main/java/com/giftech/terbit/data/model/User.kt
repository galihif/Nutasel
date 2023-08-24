package com.giftech.terbit.data.model

import android.os.Parcelable
import com.giftech.terbit.ui.components.enums.KategoriIMTEnum
import com.giftech.terbit.ui.components.enums.KategoriStatusGiziEnum
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val nama:String = "",
    val tinggi:Int = 170,
    val berat:Int = 70,
    val usia:Int = 18,
    val isMale:Boolean = true,
):Parcelable{
    var tinggiInM = tinggi.toDouble()/100
    var skorIMT = berat.toDouble() / (tinggiInM * tinggiInM)

    var kategoriIMT = when {
        skorIMT < 17.0 -> KategoriIMTEnum.SANGAT_KURUS
        skorIMT < 18.5 -> KategoriIMTEnum.KURUS
        skorIMT < 25 -> KategoriIMTEnum.NORMAL
        skorIMT < 27 -> KategoriIMTEnum.GEMUK
        else -> KategoriIMTEnum.OBESITAS
    }

    var kategoriStatusGizi = when {
        skorIMT/usia < 0.9 -> KategoriStatusGiziEnum.GIZI_BURUK
        skorIMT/usia < 1.0 -> KategoriStatusGiziEnum.GIZI_KURANG
        skorIMT/usia < 1.3 -> KategoriStatusGiziEnum.GIZI_BAIK
        skorIMT/usia < 1.4 -> KategoriStatusGiziEnum.GIZI_LEBIH
        skorIMT/usia > 1.4 -> KategoriStatusGiziEnum.OBESITAS
        else -> KategoriStatusGiziEnum.GIZI_BAIK
    }
}
