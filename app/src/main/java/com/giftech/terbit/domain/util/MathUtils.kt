package com.giftech.terbit.domain.util

import java.util.Locale

fun percentageOf(value: Int, total: Int): Int {
    return (value.toDouble() / total * 100).toInt()
}

fun Double.toSinglePrecision(): Double {
    return String.format(
        Locale.ENGLISH, "%.1f", this
    ).toDouble()
}