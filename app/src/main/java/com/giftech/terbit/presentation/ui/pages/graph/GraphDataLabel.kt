package com.giftech.terbit.presentation.ui.pages.graph

import com.patrykandpatrick.vico.core.chart.values.ChartValues
import com.patrykandpatrick.vico.core.formatter.ValueFormatter

class AsaqDataLabelFormatter : ValueFormatter {
    
    override fun formatValue(
        value: Float,
        chartValues: ChartValues,
    ): CharSequence {
        return "${value.toInt()}j\n${((value - value.toInt()) * 60).toInt()}m"
    }
    
}

class FfqScoreDataLabelFormatter : ValueFormatter {
    
    override fun formatValue(
        value: Float,
        chartValues: ChartValues,
    ): CharSequence {
        return "${value.toInt()}"
    }
    
}