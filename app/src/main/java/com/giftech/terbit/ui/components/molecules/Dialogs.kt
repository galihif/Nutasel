package com.giftech.terbit.ui.components.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.giftech.terbit.R

@ExperimentalMaterial3Api
@Composable
fun KTRDialog(
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
    ) {
        Surface(
            modifier = Modifier.wrapContentSize(),
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = "KTR Nutrisionis"
                )
                Image(
                    painter = painterResource(id = R.drawable.ktr),
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth(),
                )
                Text(
                    text = "Tenaga Kesehatan"
                )
                Text(
                    text = "Berlaku sampai 17 Maret 2028"
                )
            }
        }
    }
}