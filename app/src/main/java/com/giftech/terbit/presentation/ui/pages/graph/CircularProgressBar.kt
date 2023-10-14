package com.giftech.terbit.presentation.ui.pages.graph

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressBar(
    value: Int,
    modifier: Modifier = Modifier,
    maxValue: Int = 100,
    diameterSize: Dp = 144.dp,
    enableAnimation: Boolean = true,
) {
    var progress by remember { mutableFloatStateOf(0.0f) }
    val progressAnimation by animateFloatAsState(
        targetValue = progress,
        animationSpec = TweenSpec(
            durationMillis = if (enableAnimation) 1000 else 0,
            easing = FastOutSlowInEasing,
        ),
        label = "FloatAnimation",
    )
    LaunchedEffect(value) {
        progress = value / maxValue.toFloat()
    }
    
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
    ) {
        CircularProgressIndicator(
            progress = progressAnimation,
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.outlineVariant.copy(0.5f),
            strokeCap = StrokeCap.Round,
            strokeWidth = 10.dp,
            modifier = Modifier
                .size(diameterSize),
        )
        Text(
            text = "$value%",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
        )
    }
}