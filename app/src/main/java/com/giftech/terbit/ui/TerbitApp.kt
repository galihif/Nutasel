package com.giftech.terbit.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.giftech.terbit.domain.model.User
import com.giftech.terbit.ui.components.enums.HeroEnum
import com.giftech.terbit.ui.components.templates.OnboardLoading
import com.giftech.terbit.ui.components.templates.Onboarding
import com.giftech.terbit.ui.pages.asaq.AsaqScreen
import com.giftech.terbit.ui.pages.ffq.list.FfqListScreen
import com.giftech.terbit.ui.pages.ffq.main.FfqMainScreen
import com.giftech.terbit.ui.pages.ffq.result.FfqResultScreen
import com.giftech.terbit.ui.pages.hasil_imt.HasilIMTScreen
import com.giftech.terbit.ui.pages.hasil_tingkat_pemantauan.HasilTPScreen
import com.giftech.terbit.ui.pages.input_data_diri.InputDataDiriScreen
import com.giftech.terbit.ui.pages.onboarding.FfqOnboardingScreen
import com.giftech.terbit.ui.pages.profesional.ProfesionalScreen
import com.giftech.terbit.ui.route.Screen
import com.giftech.terbit.ui.utils.Constants

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
                onNext = { user ->
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
        composable(Screen.HasilIMT.route) {
            val user = navHostController.previousBackStackEntry?.savedStateHandle?.get<User>("user")
            if (user != null) {
                HasilIMTScreen(
                    onNext = {
                        navHostController.apply {
                            currentBackStackEntry?.savedStateHandle?.set("user", it)
                            navigate(Screen.OnboardingASAQ1.route)
                        }
                    },
                    onBack = {
                        navHostController.popBackStack(
                            route = Screen.InputDataDiri.route,
                            inclusive = false
                        )
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
                    navHostController.navigate(Screen.ASAQ.route)
                },
                hero = HeroEnum.AsaqOnboard3
            )
        }
        composable(Screen.ASAQ.route) {
            AsaqScreen(
                onBack = {
                    navHostController.popBackStack()
                },
                onNext = {
                    navHostController.navigate(Screen.FfqOnboarding.route)
                },
            )
        }
        
        // Onboarding
        composable(Screen.FfqOnboarding.route) {
            FfqOnboardingScreen(
                navController = navHostController,
            )
        }
        
        // FFQ
        composable(
            route = Screen.FfqMain.route,
            arguments = listOf(
                navArgument(Constants.EXTRAS.PROGRAM_ID) { type = NavType.IntType }
            ),
        ) {
            val programId = it.arguments?.getInt(Constants.EXTRAS.PROGRAM_ID) ?: -1
            FfqMainScreen(
                programId = programId,
                navController = navHostController,
            )
        }
        
        composable(
            route = Screen.FfqList.route,
            arguments = listOf(
                navArgument(Constants.EXTRAS.PROGRAM_ID) { type = NavType.IntType },
                navArgument(Constants.EXTRAS.FFQ_FOOD_CATEGORY_ID) { type = NavType.IntType },
            ),
        ) {
            val programId = it.arguments?.getInt(Constants.EXTRAS.PROGRAM_ID) ?: -1
            val foodCategoryId = it.arguments?.getInt(Constants.EXTRAS.FFQ_FOOD_CATEGORY_ID) ?: -1
            FfqListScreen(
                programId = programId,
                foodCategoryId = foodCategoryId,
                navController = navHostController,
            )
        }
        
        composable(
            route = Screen.FfqResult.route,
            arguments = listOf(
                navArgument(Constants.EXTRAS.PROGRAM_ID) { type = NavType.IntType }
            ),
        ) {
            val programId = it.arguments?.getInt(Constants.EXTRAS.PROGRAM_ID) ?: -1
            FfqResultScreen(
                programId = programId,
                navController = navHostController,
            )
        }
        
        composable(Screen.OnboardingTingkatPemantauan.route) {
            val totalScore =
                navHostController.previousBackStackEntry?.savedStateHandle?.get<Int>("total_score")
            OnboardLoading(
                onNext = {
                    navHostController.apply {
                        currentBackStackEntry?.savedStateHandle?.set("total_score", totalScore)
                        navigate(Screen.HasilTingkatPemantauan.route)
                    }
                },
                hero = HeroEnum.LoadingHasilTP
            )
        }
        
        composable(Screen.HasilTingkatPemantauan.route) {
            val totalScore =
                navHostController.previousBackStackEntry?.savedStateHandle?.get<Int>("total_score")
            if (totalScore != null && totalScore > 0) {
                HasilTPScreen(
                    totalScore = totalScore,
                    onNext = {
                        // go to home
                    }
                )
            }
        }
        
        composable(Screen.Profesional.route) {
            ProfesionalScreen()
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun PreviewTerbitApp() {
    TerbitApp()
}