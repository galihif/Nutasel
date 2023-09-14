package com.giftech.terbit.ui.pages.monitoring

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.giftech.terbit.ui.components.molecules.ActivityCalendar
import com.giftech.terbit.ui.components.molecules.Alerts
import com.giftech.terbit.ui.route.Screen
import com.giftech.terbit.ui.theme.CustomColor2
import com.giftech.terbit.ui.theme.light_onCustomColor2

@Composable
fun MonitoringScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: MonitoringViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    
    MonitoringContent(
        state = state,
        navController = navController,
        modifier = modifier,
    )
}

@Composable
private fun MonitoringContent(
    state: MonitoringState,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(24.dp),
        modifier = modifier,
    ) {
        item {
            Text(
                text = "Pantau Tingkat Aktivitas Kamu!",
                style = MaterialTheme.typography.titleMedium,
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            ActivityCalendar(
                dateList = state.completedDayList,
            )
            Spacer(modifier = Modifier.height(16.dp))
            CalendarLegend(
                text = "Mengerjakan aktivitas",
                color = MaterialTheme.colorScheme.primary,
            )
            
            if (state.isWeeklyProgramAvailable.not()) {
                Spacer(modifier = Modifier.height(24.dp))
                
                Alerts(
                    text = "Program dimulai pada ${state.weeklyProgramOpeningDate}",
                    icon = Icons.Rounded.AccessTime,
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
        
        items(
            items = state.weeklyProgramList.keys.toList(),
            key = { it },
        ) { week ->
            val status = when {
                state.isWeeklyProgramAvailable.not() -> MonitoringStatus.NotAvailable
                
                state.weeklyProgramList[week]!!.all { it.isCompleted } -> MonitoringStatus.Done
                
                // If not done:
                week == state.weeklyProgramList.keys.first() -> MonitoringStatus.NotDone
                
                week != state.weeklyProgramList.keys.first() &&
                        state.weeklyProgramList[week - 1]!!.all { it.isCompleted } -> MonitoringStatus.NotDone
                
                week != state.weeklyProgramList.keys.first() &&
                        state.weeklyProgramList[week - 1]!!.any { !it.isCompleted } -> MonitoringStatus.NotAvailable
                
                
                else -> MonitoringStatus.NotAvailable
            }
            MonitoringItem(
                text = "Minggu $week",
                status = status,
                onClick = if (status == MonitoringStatus.NotAvailable) {
                    null
                } else {
                    {
                        navController.navigate(
                            Screen.MonitoringDetails.route
                        )
                    }
                },
            )
            Spacer(modifier = Modifier.height(14.dp))
        }
    }
}

@Composable
private fun CalendarLegend(
    text: String,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = color,
                    shape = CircleShape,
                )
                .size(12.dp),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = color,
        )
    }
}

enum class MonitoringStatus {
    Done,
    NotDone,
    NotAvailable,
}

@Composable
private fun MonitoringItem(
    text: String,
    status: MonitoringStatus,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    when (status) {
        MonitoringStatus.Done -> {
            MonitoringItemBase(
                text = text,
                icon = Icons.Rounded.Check,
                contentDescription = "Sudah selesai",
                color = light_onCustomColor2,
                backgroundColor = CustomColor2,
                onClick = onClick,
                modifier = modifier,
            )
        }
        
        MonitoringStatus.NotDone -> {
            MonitoringItemBase(
                text = text,
                icon = Icons.Rounded.PlayArrow,
                contentDescription = "Belum selesai",
                color = MaterialTheme.colorScheme.onPrimary,
                backgroundColor = MaterialTheme.colorScheme.primary,
                onClick = onClick,
                modifier = modifier,
            )
        }
        
        MonitoringStatus.NotAvailable -> {
            MonitoringItemBase(
                text = text,
                icon = Icons.Outlined.Lock,
                contentDescription = "Belum tersedia",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                onClick = onClick,
                modifier = modifier,
            )
        }
    }
}

@Composable
private fun MonitoringItemBase(
    text: String,
    icon: ImageVector,
    contentDescription: String,
    color: Color,
    backgroundColor: Color,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .background(backgroundColor)
            .clickable(enabled = onClick != null) { onClick!!() }
            .padding(horizontal = 24.dp, vertical = 14.dp),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = color,
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = color,
        )
    }
}