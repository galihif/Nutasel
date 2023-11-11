package com.giftech.terbit.presentation.ui.pages.graph

import androidx.compose.animation.core.snap
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.giftech.terbit.presentation.util.toTypeface
import com.patrykandpatrick.vico.compose.axis.axisLabelComponent
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberEndAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.compose.chart.entry.defaultDiffAnimationSpec
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
import com.patrykandpatrick.vico.core.formatter.ValueFormatter
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
    showMarkerLabel: Boolean = true,
    enableAnimation: Boolean = true,
    showDataLabel: Boolean = false,
    dataLabelFormatter: ValueFormatter? = null,
    dataLabelRotationDegrees: Float = 0f,
) {
    ProvideChartStyle(rememberChartStyle()) {
        val chartEntryModelProducer = remember(entries) {
            ChartEntryModelProducer(entries)
        }
        
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
            labelRotationDegrees = 90f,
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
        
        val marker = rememberMarker(
            labelFormatter = labelFormatter,
        ).takeIf { showMarkerLabel }
        
        val diffAnimationSpec = remember(enableAnimation) {
            if (enableAnimation) {
                defaultDiffAnimationSpec
            } else {
                snap()
            }
        }
        
        Chart(
            chart = columnChart(
                spacing = 16.dp,
                innerSpacing = 6.dp,
                axisValuesOverrider = remember(maxY) {
                    object : AxisValuesOverrider<ChartEntryModel> {
                        override fun getMinY(model: ChartEntryModel) = 0f
                        override fun getMaxY(model: ChartEntryModel) = maxY.toFloat()
                    }
                },
                dataLabelRotationDegrees = dataLabelRotationDegrees,
            ).apply {
                if (showDataLabel.not()) dataLabel = null
                if (dataLabelFormatter != null) dataLabelValueFormatter = dataLabelFormatter
            },
            chartModelProducer = chartEntryModelProducer,
            bottomAxis = xAxis,
            endAxis = yAxis,
            marker = marker,
            chartScrollSpec = rememberChartScrollSpec(isScrollEnabled = false),
            diffAnimationSpec = diffAnimationSpec,
            runInitialAnimation = remember { enableAnimation },
            modifier = modifier
                .height(220.dp),
        )
    }
}