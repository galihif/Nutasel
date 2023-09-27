package com.giftech.terbit.domain.model

data class WeeklyAsaqChart(
    val entry: List<AsaqResponse>,
    val xLabels: List<String>,
    val maxY: Int,
    val yLabelCount: Int,
)