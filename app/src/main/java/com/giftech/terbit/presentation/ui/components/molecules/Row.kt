package com.giftech.terbit.presentation.ui.components.molecules

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.giftech.terbit.presentation.ui.components.enums.ProfMenuEnum

@Composable
fun IconTextRow(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    text: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, "")
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun SegmentedButtonRow(
    selected: ProfMenuEnum,
    onSelected: (ProfMenuEnum) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        OutlinedButton(
            onClick = {
                onSelected(ProfMenuEnum.INFO)
            },
            shape = RoundedCornerShape(
                topStart = 24.dp,
                topEnd = 0.dp,
                bottomEnd = 0.dp,
                bottomStart = 24.dp
            ),
            modifier = Modifier.weight(1f),
            colors = if (selected == ProfMenuEnum.INFO) ButtonDefaults.outlinedButtonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ) else ButtonDefaults.outlinedButtonColors()
        ) {
            AnimatedVisibility(visible = selected== ProfMenuEnum.INFO) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "",
                )
            }
            Text(text = ProfMenuEnum.INFO.title)
        }
        OutlinedButton(
            onClick = {
                onSelected(ProfMenuEnum.KREDIBILITAS)
            },
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 24.dp,
                bottomEnd = 24.dp,
                bottomStart = 0.dp
            ),
            modifier = Modifier.weight(1f),
            colors = if (selected == ProfMenuEnum.KREDIBILITAS) ButtonDefaults.outlinedButtonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ) else ButtonDefaults.outlinedButtonColors()
        ) {
            AnimatedVisibility(visible = selected== ProfMenuEnum.KREDIBILITAS) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "",
                )
            }
            Text(text = ProfMenuEnum.KREDIBILITAS.title, textAlign = TextAlign.Center, maxLines = 1, modifier = Modifier.weight(1f), overflow = TextOverflow.Ellipsis)
        }
    }
}