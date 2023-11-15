package com.giftech.terbit.domain.enums

enum class FfqFrequency(val freqId: String, val score: Int) {
    NEVER("never", 0),
    MONTH_2("month_2", 5),
    WEEK_1_2("week_1_2", 10),
    WEEK_3_6("week_3_6", 15),
    DAY_1("day_1", 25),
    DAY_3("day_3", 50),
}