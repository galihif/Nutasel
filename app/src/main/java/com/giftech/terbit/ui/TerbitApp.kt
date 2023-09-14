package com.giftech.terbit.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.giftech.terbit.ui.components.enums.HeroEnum
import com.giftech.terbit.ui.components.molecules.BottomNavigation
import com.giftech.terbit.ui.components.templates.OnboardLoading
import com.giftech.terbit.ui.components.templates.Onboarding
import com.giftech.terbit.ui.pages.article.ArticleScreen
import com.giftech.terbit.ui.pages.asaq.prepost.AsaqScreen
import com.giftech.terbit.ui.pages.asaq.weekly.WeeklyAsaqScreen
import com.giftech.terbit.ui.pages.ffq.list.FfqListScreen
import com.giftech.terbit.ui.pages.ffq.main.FfqMainScreen
import com.giftech.terbit.ui.pages.ffq.result.FfqResultScreen
import com.giftech.terbit.ui.pages.graph.GraphScreen
import com.giftech.terbit.ui.pages.hasil_imt.HasilIMTScreen
import com.giftech.terbit.ui.pages.hasil_tingkat_pemantauan.HasilTPScreen
import com.giftech.terbit.ui.pages.home.HomeScreen
import com.giftech.terbit.ui.pages.input_data_diri.InputDataDiriScreen
import com.giftech.terbit.ui.pages.monitoring.MonitoringScreen
import com.giftech.terbit.ui.pages.monitoringdetails.MonitoringDetailsScreen
import com.giftech.terbit.ui.pages.notificationlist.NotificationListScreen
import com.giftech.terbit.ui.pages.onboarding.FfqOnboardingScreen
import com.giftech.terbit.ui.pages.profesional.ProfesionalScreen
import com.giftech.terbit.ui.pages.profile.EditProfileScreen
import com.giftech.terbit.ui.pages.profile.ProfileScreen
import com.giftech.terbit.ui.route.BottomNavItem
import com.giftech.terbit.ui.route.Screen
import com.giftech.terbit.ui.utils.Constants

@ExperimentalMaterial3Api
@Composable
fun TerbitApp(
    modifier: Modifier = Modifier,
) {
    val navHostController = rememberNavController()
    
    val navigationItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Monitoring,
        BottomNavItem.Graph,
        BottomNavItem.Profile,
    )
    
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    Scaffold(
        bottomBar = {
            if (currentRoute in navigationItems.map { it.screen.route }) {
                BottomNavigation(
                    navController = navHostController,
                    navigationItems = navigationItems,
                )
            }
        },
        modifier = modifier,
    ) { innerPadding ->
        NavHost(
            navController = navHostController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Input Data Diri
            composable(Screen.InputDataDiri.route) {
                InputDataDiriScreen(
                    onNext = {
                        navHostController.navigate(Screen.OnboardingIMT.route)
                    }
                )
            }
            
            // Onboarding IMT
            composable(Screen.OnboardingIMT.route) {
                OnboardLoading(
                    onNext = {
                        navHostController.navigate(Screen.HasilIMT.route)
                    },
                    hero = HeroEnum.LoadingIMT
                )
            }
            
            // Hasil IMT
            composable(Screen.HasilIMT.route) {
                HasilIMTScreen(
                    onNext = {
                        navHostController.navigate(Screen.OnboardingASAQ1.route)
                    },
                    onBack = {
                        navHostController.popBackStack(
                            route = Screen.InputDataDiri.route,
                            inclusive = false
                        )
                    },
                )
            }
            
            // Onboarding ASAQ 1
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
            
            // Onboarding ASAQ 2
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
            
            // Onboarding ASAQ 3
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
            
            // ASAQ
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
            
            // FFQ Onboarding
            composable(Screen.FfqOnboarding.route) {
                FfqOnboardingScreen(
                    navController = navHostController,
                )
            }
            
            //Onboarding Tingkat Pemantauan
            composable(Screen.OnboardingTingkatPemantauan.route) {
                OnboardLoading(
                    onNext = {
                        navHostController.navigate(Screen.HasilTingkatPemantauan.route)
                    },
                    hero = HeroEnum.LoadingHasilTP
                )
            }
            
            //Hasil Tingkat Pemantauan
            composable(Screen.HasilTingkatPemantauan.route) {
                HasilTPScreen(
                    onNext = {
                        navHostController.navigate(Screen.Home.route)
                    }
                )
            }
            
            // Homepage
            composable(Screen.Home.route) {
                HomeScreen(
                    navController = navHostController,
                )
            }
            
            composable(Screen.Monitoring.route) {
                MonitoringScreen(
                    navController = navHostController,
                )
            }
            
            composable(Screen.Graph.route) {
                GraphScreen()
            }
            
            composable(Screen.Profile.route) {
                ProfileScreen(
                    onEdit = {
                        navHostController.navigate(Screen.EditProfile.route)
                    }
                )
            }
            
            composable(Screen.EditProfile.route) {
                EditProfileScreen(
                    onBack = {
                        navHostController.popBackStack()
                    }
                )
            }
            
            composable(Screen.NotificationList.route) {
                NotificationListScreen(
                    navController = navHostController,
                )
            }
            
            composable(Screen.Profesional.route) {
                ProfesionalScreen()
            }
            
            composable(
                route = Screen.MonitoringDetails.route,
                arguments = listOf(
                    navArgument(Constants.EXTRAS.WEEK) { type = NavType.IntType }
                ),
            ) {
                val week = it.arguments?.getInt(Constants.EXTRAS.WEEK) ?: -1
                MonitoringDetailsScreen(
                    week = week,
                    navController = navHostController,
                )
            }
            
            // FFQ Main
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
                val foodCategoryId =
                    it.arguments?.getInt(Constants.EXTRAS.FFQ_FOOD_CATEGORY_ID) ?: -1
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
            
            composable(
                route = Screen.Article.route,
                arguments = listOf(
                    navArgument(Constants.EXTRAS.WEEK) { type = NavType.IntType },
                    navArgument(Constants.EXTRAS.DAY) { type = NavType.IntType },
                ),
            ) {
                val week = it.arguments?.getInt(Constants.EXTRAS.WEEK) ?: -1
                val day = it.arguments?.getInt(Constants.EXTRAS.DAY) ?: -1
                ArticleScreen(week, day)
            }
            
            composable(
                route = Screen.WeeklyAsaq.route,
                arguments = listOf(
                    navArgument(Constants.EXTRAS.PROGRAM_ID) { type = NavType.IntType }
                ),
            ) {
                val programId = it.arguments?.getInt(Constants.EXTRAS.PROGRAM_ID) ?: -1
                WeeklyAsaqScreen(
                    programId = programId,
                    navController = navHostController,
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun PreviewTerbitApp() {
    TerbitApp()
}