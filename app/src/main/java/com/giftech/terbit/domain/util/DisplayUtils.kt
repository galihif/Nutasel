package com.giftech.terbit.domain.util

fun Double.idFormat(): String {
    return String.format(LOCALE_INDONESIAN, "%.1f", this)
}