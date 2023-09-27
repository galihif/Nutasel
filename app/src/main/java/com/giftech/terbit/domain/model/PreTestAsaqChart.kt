package com.giftech.terbit.domain.model

data class PreTestAsaqChart(
    val entry: List<Asaq>,
    val xLabels: List<String>,
    val maxY: Int,
    val yLabelCount: Int,
)