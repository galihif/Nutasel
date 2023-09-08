package com.giftech.terbit.domain.util

import java.time.LocalDateTime
import java.time.ZoneId

fun LocalDateTime.toMillis(): Long {
    return this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}