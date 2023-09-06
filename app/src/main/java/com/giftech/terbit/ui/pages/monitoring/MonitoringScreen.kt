package com.giftech.terbit.ui.pages.monitoring

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import androidx.navigation.NavController
import com.giftech.terbit.ui.components.molecules.Alerts
import com.giftech.terbit.ui.components.molecules.Calendar
import com.giftech.terbit.ui.components.molecules.clickable
import com.giftech.terbit.ui.route.Screen
import com.giftech.terbit.ui.theme.CustomColor2
import com.giftech.terbit.ui.theme.dark_onCustomColor3
import com.giftech.terbit.ui.theme.light_CustomColor3Container
import com.giftech.terbit.ui.theme.light_onCustomColor2

// TODO: Logic menyusul
@Composable
fun MonitoringScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    MonitoringContent(
        navController = navController,
        modifier = modifier,
    )
}

@Composable
private fun MonitoringContent(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
    ) {
        Text(
            text = "Pantau Tingkat Aktivitas Kamu!",
            style = MaterialTheme.typography.titleMedium,
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Calendar()
        Spacer(modifier = Modifier.height(16.dp))
        CalendarLegend(
            text = "Mengerjakan aktivitas",
            color = MaterialTheme.colorScheme.primary,
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Alerts(
            text = "Program dimulai pada 15 - 10 - 2023",
            icon = Icons.Rounded.AccessTime,
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        MonitoringItem(
            text = "Minggu Kesatu",
            status = MonitoringStatus.Done,
            onClick = {
                navController.navigate(
                    Screen.MonitoringDetails.route
                )
            },
        )
        Spacer(modifier = Modifier.height(12.dp))
        MonitoringItem(
            text = "Minggu Kedua",
            status = MonitoringStatus.NotDone,
            onClick = {
                navController.navigate(
                    Screen.MonitoringDetails.route
                )
            },
        )
        Spacer(modifier = Modifier.height(12.dp))
        MonitoringItem(
            text = "Minggu Ketiga",
            status = MonitoringStatus.NotAvailable,
            onClick = null,
        )
        Spacer(modifier = Modifier.height(12.dp))
        MonitoringItem(
            text = "Minggu Keempat",
            status = MonitoringStatus.NotAvailable,
            onClick = null,
        )
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
            .padding(horizontal = 16.dp, vertical = 12.dp),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = color,
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = color,
        )
    }
}