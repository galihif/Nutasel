package com.giftech.terbit.ui.pages.hasil_tingkat_pemantauan

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PendingActions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.giftech.terbit.R
import com.giftech.terbit.ui.components.atoms.PrimaryButton
import com.giftech.terbit.ui.components.molecules.IconTextRow

@Composable
fun HasilTPScreen(
    totalScore: Int,
    onNext: () -> Unit
) {
    var title by remember {
        mutableStateOf("Ringan")
    }
    var desc by remember {
        mutableStateOf("Aktivitas sedentari kamu ringan nih, yuk kita perbaiki lagi agar pola hidup kamu menjadi lebih baik.")
    }
    LaunchedEffect(totalScore) {
        when {
            totalScore <= 30 -> {
                title = "Ringan"
                desc =
                    "Aktivitas sedentari kamu ringan nih, yuk kita perbaiki lagi agar pola hidup kamu menjadi lebih baik."
            }

            totalScore <= 54 -> {
                title = "Sedang"
                desc =
                    "Aktivitas sedentari dan pola makanmu sepertinya kurang baik, yuk kita perbaiki dengan aktivitas ini ya"
            }

            else -> {
                title = "Berat"
                desc =
                    "Wah aktivitas sedentari kamu berat, yuk lakukan kegiatan dibawah ini agar pola hidupmu jauh lebih baik"
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.check_circle),
                    contentDescription = ""
                )
                Text(
                    text = "Tingkat pemantauan kamu adalah",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 24.dp)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Text(
                        text = desc,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                    IconTextRow(icon = Icons.Default.MenuBook, text = "Membaca 1 Artikel perminggu")
                    IconTextRow(icon = Icons.Default.PendingActions, text = "Pemantauan Aktivitas Sedentari")
                    IconTextRow(icon = Icons.Default.Fastfood, text = "Pemantauan Frekuensi Makanan")
                }
            }

        }
        PrimaryButton(
            text = "Selanjutnya",
            onClick = onNext,
            modifier = Modifier.fillMaxWidth()
        )
    }
}