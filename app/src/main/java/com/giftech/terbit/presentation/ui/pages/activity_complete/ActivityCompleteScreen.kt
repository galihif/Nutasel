package com.giftech.terbit.presentation.ui.pages.activity_complete

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.giftech.terbit.R
import com.giftech.terbit.presentation.ui.components.atoms.PrimaryButton
import com.giftech.terbit.presentation.ui.components.enums.HeroEnum
import com.giftech.terbit.presentation.ui.components.molecules.HeroColumn

@ExperimentalMaterial3Api
@Composable
fun ActivityCompleteScreen(
    week: Int,
    onNext: () -> Unit = {},
) {
    var hero by remember {
        mutableStateOf(HeroEnum.ActComplete1)
    }
    LaunchedEffect(week) {
        hero = when (week) {
            1 -> HeroEnum.ActComplete1
            2 -> HeroEnum.ActComplete2
            3 -> HeroEnum.ActComplete3
            4 -> HeroEnum.ActComplete4
            else -> HeroEnum.ActComplete1
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeroColumn(
            modifier = Modifier.padding(vertical = 16.dp),
            title = hero.title,
            description = hero.description,
            imageRes = hero.image,
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
                WeekCompleteColumn(
                    text = "Mn1",
                    isComplete = week >= 1
                )
                WeekCompleteColumn(
                    text = "Mn2",
                    isComplete = week >= 2
                )
                WeekCompleteColumn(
                    text = "Mn3",
                    isComplete = week >= 3
                )
                WeekCompleteColumn(
                    text = "Mn4",
                    isComplete = week >= 4
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
fun WeekCompleteColumn(
    text: String,
    isComplete: Boolean = false
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = text, style = MaterialTheme.typography.titleMedium)
        Image(
            painter = if (isComplete) painterResource(id = R.drawable.vector_act_complete_circle) else
                painterResource(id = R.drawable.vector_act_incomplete_circle),
            contentDescription = ""
        )
    }
}