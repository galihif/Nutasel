package com.giftech.terbit.ui.components.atoms

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun MyFilterChips(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onSelectedChange: (Boolean) -> Unit,
    text: String
) {
    ElevatedFilterChip(
        modifier = modifier.fillMaxWidth(),
        selected = selected,
        onClick = { onSelectedChange(!selected) },
        label = {
            Text(
                text = text,
                modifier = Modifier.padding(16.dp),
            )
        },
        leadingIcon = {
            AnimatedVisibility(visible = selected) {
                Icon(
                    Icons.Default.Check,
                    ""
                )
            }
        },
    )
}