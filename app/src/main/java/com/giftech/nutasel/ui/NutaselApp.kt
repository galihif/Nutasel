package com.giftech.nutasel.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.giftech.nutasel.data.model.Asaq
import com.giftech.nutasel.ui.components.enums.HeroEnum
import com.giftech.nutasel.ui.components.templates.Onboarding
import com.giftech.nutasel.ui.pages.InputDataDiriScreen
import com.giftech.nutasel.ui.pages.asaq.AsaqScreen
import com.giftech.nutasel.ui.route.Screen
import com.giftech.nutasel.utils.DataProvider

@ExperimentalMaterial3Api
@Composable
fun NutaselApp() {
    val navHostController = rememberNavController()
    var asaqMap by remember { mutableStateOf(DataProvider.asaqMap()) }
    var currentAsaqNumber by remember { mutableStateOf(1) }
    var currentAsaq by remember { mutableStateOf(asaqMap[currentAsaqNumber]) }
    LaunchedEffect(currentAsaqNumber) {
        currentAsaq = asaqMap[currentAsaqNumber]
    }
    NavHost(
        navController = navHostController,
        startDestination = Screen.InputDataDiri.route
    ) {
        composable(Screen.InputDataDiri.route) {
            InputDataDiriScreen(
                onNext = {
                    navHostController.navigate(Screen.OnboardingASAQ1.route)
                }
            )
        }
        composable(Screen.OnboardingASAQ1.route) {
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
        composable(Screen.OnboardingASAQ2.route) {
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
        composable(Screen.OnboardingASAQ3.route) {
            Onboarding(
                onBack = {
                    navHostController.popBackStack()
                },
                onNext = {
                    navHostController.navigate(Screen.ASAQ1.route)
                },
                hero = HeroEnum.AsaqOnboard3
            )
        }
        composable(Screen.ASAQ1.route) {
            AsaqScreen(
                asaq = currentAsaq,
                onBack = {
                    if (currentAsaqNumber > 1) {
                        currentAsaqNumber--
                    } else {
                        navHostController.popBackStack()
                        asaqMap = DataProvider.asaqMap()
                    }
                },
                onNext = { newAsaq ->
                    asaqMap = asaqMap.toMutableMap()
                        .apply { this[currentAsaqNumber] = newAsaq } as HashMap<Int, Asaq>
                    currentAsaq = asaqMap[currentAsaqNumber]
                    if (currentAsaqNumber < 12) {
                        currentAsaqNumber++
                    } else {
                        navHostController.navigate(Screen.OnboardingASAQ1.route)
                    }
                }
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