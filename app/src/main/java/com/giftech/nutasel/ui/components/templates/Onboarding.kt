package com.giftech.nutasel.ui.components.templates

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.giftech.nutasel.ui.components.atoms.PrimaryButton
import com.giftech.nutasel.ui.components.enums.HeroEnum
import com.giftech.nutasel.ui.components.molecules.HeroColumn

@ExperimentalMaterial3Api
@Composable
fun Onboarding(
    onBack: (() -> Unit)? = null,
    onNext: () -> Unit,
    hero:HeroEnum
) {
    Scaffold(
        topBar = {
            if (onBack != null) {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            HeroColumn(
                hero = hero
            )
            PrimaryButton(
                text = "Selanjutnya",
                onClick = onNext,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}