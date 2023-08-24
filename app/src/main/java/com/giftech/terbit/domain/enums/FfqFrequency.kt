package com.giftech.terbit.domain.enums

enum class FfqFrequency(val freqId: String, val score: Int) {
    DAY_3("day_3", 50),
    DAY_1("day_1", 25),
    WEEK_3_6("week_3_6", 15),
    WEEK_1_2("week_1_2", 10),
    MONTH_2("month_2", 5),
    NEVER("never", 0),
}