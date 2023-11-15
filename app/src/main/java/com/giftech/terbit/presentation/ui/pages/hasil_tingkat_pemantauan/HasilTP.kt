package com.giftech.terbit.presentation.ui.pages.hasil_tingkat_pemantauan

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PendingActions
import androidx.compose.material.icons.outlined.LocalFireDepartment
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.giftech.terbit.R
import com.giftech.terbit.presentation.ui.components.atoms.PrimaryButton
import com.giftech.terbit.presentation.ui.components.molecules.IconTextRow
import com.giftech.terbit.presentation.ui.theme.light_CustomColor1Container
import com.giftech.terbit.presentation.ui.theme.light_CustomColor2Container
import com.giftech.terbit.presentation.ui.theme.light_CustomColor3
import com.giftech.terbit.presentation.ui.theme.light_onCustomColor1Container
import com.giftech.terbit.presentation.ui.theme.light_onCustomColor2Container

@Composable
fun HasilTPScreen(
    onNext: () -> Unit,
    viewModel: HasilTPViewModel = hiltViewModel(),
) {
    val sedenterType by remember {
        viewModel.sedenterType
    }.collectAsState()
    val avgHours by remember {
        viewModel.avgHours
    }.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Pemantauan Kamu",
                    style = MaterialTheme.typography.titleLarge
                )
                Image(
                    painter = painterResource(id = R.drawable.hasil_tp_illustration),
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth(0.6f),
                )
            }
            Surface(
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurfaceVariant),
            ) {
                Row(
                    modifier = Modifier
                        .padding(vertical = 12.dp, horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AccessAlarm,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(text = "Rata - rata", style = MaterialTheme.typography.bodySmall)
                            Text(
                                text = "$avgHours jam",
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.LocalFireDepartment,
                            contentDescription = null,
                            tint = light_CustomColor3
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(text = "Kategori", style = MaterialTheme.typography.bodySmall)
                            Text(
                                text = sedenterType.title,
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }
            }
            Card(
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurfaceVariant),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Text(
                        text = sedenterType.description,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                    Column {
                        IconTextRow(
                            icon = Icons.Default.MenuBook,
                            text = "Membaca 2 Artikel perminggu",
                            iconColor = light_onCustomColor2Container,
                            boxColor = light_CustomColor2Container,
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        IconTextRow(
                            icon = Icons.Default.PendingActions,
                            text = "Pemantauan Aktivitas Sedenter",
                            iconColor = light_onCustomColor1Container,
                            boxColor = light_CustomColor1Container,
                        )
                    }
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