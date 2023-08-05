package com.giftech.terbit.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.giftech.terbit.data.model.User
import com.giftech.terbit.ui.components.enums.HeroEnum
import com.giftech.terbit.ui.components.templates.OnboardLoading
import com.giftech.terbit.ui.components.templates.Onboarding
import com.giftech.terbit.ui.pages.asaq.AsaqScreen
import com.giftech.terbit.ui.pages.hasil_imt.HasilIMTScreen
import com.giftech.terbit.ui.pages.input_data_diri.InputDataDiriScreen
import com.giftech.terbit.ui.route.Screen

@ExperimentalMaterial3Api
@Composable
fun TerbitApp() {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = Screen.InputDataDiri.route
    ) {
        composable(Screen.InputDataDiri.route) {
            InputDataDiriScreen(
                onNext = {user ->
                    navHostController.apply {
                        currentBackStackEntry?.savedStateHandle?.set("user", user)
                        navigate(Screen.OnboardingIMT.route)
                    }
                }
            )
        }
        composable(Screen.OnboardingIMT.route) {
            val user = navHostController.previousBackStackEntry?.savedStateHandle?.get<User>("user")
            OnboardLoading(
                onNext = {
                    navHostController.apply {
                        currentBackStackEntry?.savedStateHandle?.set("user", user)
                        navigate(Screen.HasilIMT.route)
                    }
                },
                hero = HeroEnum.LoadingIMT
            )
        }
        composable(Screen.HasilIMT.route){
            val user = navHostController.previousBackStackEntry?.savedStateHandle?.get<User>("user")
            if (user != null) {
                HasilIMTScreen(
                    onNext = {
                        navHostController.apply {
                            currentBackStackEntry?.savedStateHandle?.set("user", user)
                            navigate(Screen.OnboardingASAQ1.route)
                        }
                    },
                    onBack = {
                        navHostController.popBackStack(route = Screen.InputDataDiri.route, inclusive = false)
                    },
                    user = user
                )
            }
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
                onBack = {
                    navHostController.popBackStack()
                },
                onNext = {
                    navHostController.navigate(Screen.InputDataDiri.route)
                },
            )
        }

    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun PreviewTerbitApp() {
    TerbitApp()
}