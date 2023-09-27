package com.giftech.terbit.domain.model

data class FfqCategoryChart(
    val entry: List<FfqQuestion>,
    val xLabels: List<String>,
    val yLabels: List<String>,
)