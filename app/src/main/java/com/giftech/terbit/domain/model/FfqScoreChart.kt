package com.giftech.terbit.domain.model

data class FfqScoreChart(
    val entry1: List<FfqQuestion>,
    val entry2: List<FfqQuestion>,
    val xLabels: List<String>,
    val maxY: Int,
    val yLabelCount: Int,
)