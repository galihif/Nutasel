package com.giftech.terbit.presentation.ui.pages.onboarding

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.giftech.terbit.presentation.ui.components.enums.HeroEnum
import com.giftech.terbit.presentation.ui.components.templates.Onboarding
import com.giftech.terbit.presentation.ui.route.Screen

@Composable
fun AppOnboardingScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    AppOnboardingContent(
        navController = navController,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppOnboardingContent(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Onboarding(
        hero = HeroEnum.AppOnboard,
        onNext = {
            navController.navigate(
                Screen.InputDataDiri.route
            )
        },
        modifier = modifier,
    )
}