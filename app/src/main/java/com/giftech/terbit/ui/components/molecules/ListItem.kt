package com.giftech.terbit.ui.components.molecules

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun OrganisasiListItem(
    name: String,
    desc: String,
    @DrawableRes image: Int
) {
    ListItem(
        leadingContent = {
            Image(
                painter = painterResource(id = image),
                contentDescription ="",
                modifier = Modifier.size(56.dp)
            )
        },
        headlineContent = {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        supportingContent = {
            Text(
                text = desc,
                style = MaterialTheme.typography.bodySmall
            )
        }
    )
}