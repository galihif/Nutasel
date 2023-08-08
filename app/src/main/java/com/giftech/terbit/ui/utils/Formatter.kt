package com.giftech.terbit.ui.utils

import java.text.DecimalFormat

fun Double.toFormattedString():String{
    val formatter = DecimalFormat("#,###.##")
    return formatter.format(this)
}