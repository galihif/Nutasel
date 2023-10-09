package com.giftech.terbit.presentation.ui.pages.graph

import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.chart.values.ChartValues

class AsaqChartXValueFormatter(
    private val xLabels: List<String>,
) : AxisValueFormatter<AxisPosition.Horizontal.Bottom> {
    
    override fun formatValue(value: Float, chartValues: ChartValues): CharSequence {
        val questionNumber = value.toInt()
        val indexX = questionNumber - 1
        return xLabels[indexX]
    }
    
}