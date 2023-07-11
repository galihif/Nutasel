package com.giftech.nutasel.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.giftech.nutasel.ui.components.enums.HeroEnum
import com.giftech.nutasel.ui.components.templates.Onboarding
import com.giftech.nutasel.ui.pages.InputDataDiriScreen
import com.giftech.nutasel.ui.route.Screen

@ExperimentalMaterial3Api
@Composable
fun NutaselApp() {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = Screen.InputDataDiri.route
    ) {
        composable(Screen.InputDataDiri.route){
            InputDataDiriScreen(
                onNext = {
                    navHostController.navigate(Screen.OnboardingASAQ1.route)
                }
            )
        }
        composable(Screen.OnboardingASAQ1.route){
            Onboarding(
                onBack = {
                    navHostController.popBackStack()
                },
                onNext = {
                    navHostController.navigate(Screen.OnboardingASAQ2.route)
                },
                hero = HeroEnum.AsaqOnboard1
            )
        }
        composable(Screen.OnboardingASAQ2.route){
            Onboarding(
                onBack = {
                    navHostController.popBackStack()
                },
                onNext = {
                    navHostController.navigate(Screen.OnboardingASAQ3.route)
                },
                hero = HeroEnum.AsaqOnboard2
            )
        }
        composable(Screen.OnboardingASAQ3.route){
            Onboarding(
                onBack = {
                    navHostController.popBackStack()
                },
                onNext = {
                    navHostController.navigate(Screen.InputDataDiri.route)
                },
                hero = HeroEnum.AsaqOnboard3
            )
        }

    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun PreviewNutaselApp() {
    NutaselApp()
}