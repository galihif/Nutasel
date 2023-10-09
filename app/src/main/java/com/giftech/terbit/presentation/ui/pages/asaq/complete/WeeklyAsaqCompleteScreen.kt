package com.giftech.terbit.presentation.ui.pages.asaq.complete

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.giftech.terbit.R
import com.giftech.terbit.presentation.ui.components.atoms.PrimaryButton
import com.giftech.terbit.presentation.ui.theme.dark_onCustomColor1Container
import com.giftech.terbit.presentation.ui.theme.dark_onCustomColor2Container
import com.giftech.terbit.presentation.ui.theme.light_onCustomColor1Container
import com.giftech.terbit.presentation.ui.theme.light_onCustomColor2Container

@Composable
fun WeeklyAsaqCompleteScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    WeeklyAsaqCompleteContent(
        navController = navController,
        modifier = modifier,
    )
}

@Composable
private fun WeeklyAsaqCompleteContent(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
    ) {
        Image(
            painter = painterResource(R.drawable.vector_complete_weekly_asaq),
            contentDescription = "Pantau sedenter selesai",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 48.dp),
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Text(
            text = "Horee!",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Mantap, kamu udah menyelesaikan pemantauan harian kamu!",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    shape = MaterialTheme.shapes.medium,
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                    ),
                )
                .padding(8.dp),
        ) {
            listOf(
                Stat(
                    title = "Luar Biasa",
                    value = "100%",
                    titleTextColor = light_onCustomColor2Container,
                    titleBackgroundColor = dark_onCustomColor2Container,
                ),
                Stat(
                    title = "Selesai",
                    value = "12/12",
                    titleTextColor = light_onCustomColor1Container,
                    titleBackgroundColor = dark_onCustomColor1Container,
                ),
            ).forEach { stat ->
                StatItem(
                    stat = stat,
                    modifier = Modifier.weight(1f),
                )
            }
        }
        
        Spacer(modifier = Modifier.weight(1f))
        
        PrimaryButton(
            text = "Selanjutnya",
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp),
        )
    }
}

data class Stat(
    val title: String,
    val value: String,
    val titleTextColor: Color,
    val titleBackgroundColor: Color,
)

@Composable
private fun StatItem(
    stat: Stat,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.small,
            )
            .padding(6.dp),
    ) {
        Text(
            text = stat.title,
            color = stat.titleTextColor,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(
                    color = stat.titleBackgroundColor,
                    shape = RoundedCornerShape(100),
                )
                .padding(horizontal = 18.dp, vertical = 2.dp),
        )
        Text(
            text = stat.value,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
        )
    }
}