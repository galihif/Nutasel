package com.giftech.terbit.ui.components.molecules

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.giftech.terbit.ui.theme.TerbitTheme

@Composable
fun FfqFoodItem(
    name: String,
    isChecked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f),
            )
            if (isChecked) {
                Checkbox(
                    checked = true,
                    onCheckedChange = onCheckedChange,
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Divider(
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FfqFoodItemPreview() {
    TerbitTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            FfqFoodItem(
                name = "Nasi",
                isChecked = true,
                onClick = {},
            )
        }
    }
}