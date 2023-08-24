package com.giftech.terbit.ui.components.enums

enum class TingkatAktivitasEnum(
    val title:String,
    val score:Int
) {
    DEFAULT("", 0),
    RENDAH("<2 Jam", 1),
    SEDANG("2 - 5 Jam", 2),
    TINGGI(">5 Jam", 3),
}