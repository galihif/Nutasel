package com.giftech.terbit.ui.pages.onboarding_posttest

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.giftech.terbit.ui.components.atoms.PrimaryButton
import com.giftech.terbit.ui.components.enums.HeroEnum
import com.giftech.terbit.ui.components.molecules.HeroColumn
import com.giftech.terbit.ui.theme.light_CustomColor1Container
import com.giftech.terbit.ui.theme.light_CustomColor2Container
import com.giftech.terbit.ui.theme.light_onCustomColor1Container
import com.giftech.terbit.ui.theme.light_onCustomColor2Container

@Composable
fun OnboardingPosttestScreen(
    onNext: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeroColumn(
            modifier = Modifier.padding(vertical = 16.dp),
            title = HeroEnum.OnboardPosttest.title,
            description = HeroEnum.OnboardPosttest.description,
            imageRes = HeroEnum.OnboardPosttest.image,
            imageHeight = 200
        )
        Surface(
            color = Color.Transparent,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                InfoBox(
                    title = "ASAQ",
                    value = "12",
                    color = light_CustomColor2Container,
                    contentColor = light_onCustomColor2Container
                )
                InfoBox(
                    title = "FFQ",
                    value = "7",
                    color = light_CustomColor1Container,
                    contentColor = light_onCustomColor1Container
                )
            }
        }
        PrimaryButton(
            text = "Selanjutnya",
            onClick = onNext,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun RowScope.InfoBox(
    title: String,
    value: String,
    color: Color,
    contentColor: Color
) {
    Column(
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            shape = RoundedCornerShape(6.dp),
            color = MaterialTheme.colorScheme.surface,
        ) {
            Text(
                text = title,
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(
                        RoundedCornerShape(64.dp)
                    )
                    .background(
                        color
                    )
                    .padding(horizontal = 18.dp, vertical = 2.dp),
                color = contentColor,
                style = MaterialTheme.typography.labelMedium
            )
        }
        Text(text = value, style = MaterialTheme.typography.bodyMedium)
    }
}