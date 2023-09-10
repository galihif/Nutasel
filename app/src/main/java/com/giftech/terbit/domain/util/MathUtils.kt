package com.giftech.terbit.domain.util

fun percentageOf(value: Int, total: Int): Int {
    return (value.toDouble() / total * 100).toInt()
}

fun Double.toSinglePrecision(): Double {
    return String.format("%.1f", this).toDouble()
}