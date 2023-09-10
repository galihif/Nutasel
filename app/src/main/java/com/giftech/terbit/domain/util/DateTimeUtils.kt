package com.giftech.terbit.domain.util

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private fun formatter(pattern: String): DateTimeFormatter {
    return DateTimeFormatter.ofPattern(pattern, LOCALE_INDONESIAN)
}

fun LocalDate.toString(outputPattern: String): String {
    return try {
        this.format(formatter(outputPattern))
    } catch (e: Exception) {
        e.printStackTrace()
        "-"
    }
}

fun LocalDateTime.toMillis(): Long {
    return this.atZone(
        ZoneId.systemDefault(),
    ).toInstant().toEpochMilli()
}

fun Long?.toLocalDateTime(): LocalDateTime {
    return LocalDateTime.ofInstant(
        Instant.ofEpochMilli(this ?: 0),
        ZoneId.systemDefault(),
    )
}