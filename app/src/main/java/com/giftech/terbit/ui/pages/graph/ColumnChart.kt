package com.giftech.terbit.ui.pages.graph

import androidx.compose.animation.core.snap
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.giftech.terbit.ui.utils.toTypeface
import com.patrykandpatrick.vico.compose.axis.axisLabelComponent
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberEndAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollSpec
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider
import com.patrykandpatrick.vico.core.component.text.TextComponent
import com.patrykandpatrick.vico.core.entry.ChartEntry
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.marker.MarkerLabelFormatter

@Composable
fun ColumnChart(
    entries: List<List<ChartEntry>>,
    xLabels: List<String>,
    maxY: Int,
    yLabelCount: Int,
    labelFormatter: MarkerLabelFormatter,
    modifier: Modifier = Modifier,
    yTitle: String? = null,
    xValueFormatter: AxisValueFormatter<AxisPosition.Horizontal.Bottom>? = null,
) {
    ProvideChartStyle(rememberChartStyle()) {
        val chartEntryModelProducer = ChartEntryModelProducer(entries)
        
        val xAxis = rememberBottomAxis(
            axis = null,
            guideline = null,
            tick = null,
            label = axisLabelComponent(
                verticalMargin = 4.dp,
            ),
            valueFormatter = xValueFormatter
                ?: AxisValueFormatter { value, _ ->
                    xLabels[value.toInt()]
                },
        )
        val yAxis = rememberEndAxis(
            axis = null,
            guideline = null,
            tick = null,
            title = yTitle,
            titleComponent = TextComponent.Builder().apply {
                color = MaterialTheme.colorScheme.primary.toArgb()
                typeface = MaterialTheme.typography.labelSmall.toTypeface()
            }.build(),
            label = axisLabelComponent(
                horizontalMargin = 4.dp,
            ),
            itemPlacer = AxisItemPlacer.Vertical.default(yLabelCount),
        )
        
        Chart(
            chart = columnChart(
                spacing = 16.dp,
                innerSpacing = 6.dp,
                axisValuesOverrider = object : AxisValuesOverrider<ChartEntryModel> {
                    override fun getMinY(model: ChartEntryModel) = 0f
                    override fun getMaxY(model: ChartEntryModel) = maxY.toFloat()
                },
            ),
            chartModelProducer = chartEntryModelProducer,
            bottomAxis = xAxis,
            endAxis = yAxis,
            marker = rememberMarker(
                labelFormatter = labelFormatter,
            ),
            chartScrollSpec = rememberChartScrollSpec(isScrollEnabled = false),
            diffAnimationSpec = snap(),
            modifier = modifier
                .height(220.dp),
        )
    }
}