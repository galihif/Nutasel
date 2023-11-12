package com.giftech.terbit.presentation.ui

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
import androidx.navigation.navDeepLink
import com.giftech.terbit.presentation.ui.components.enums.HeroEnum
import com.giftech.terbit.presentation.ui.components.molecules.BottomNavigation
import com.giftech.terbit.presentation.ui.components.templates.OnboardLoading
import com.giftech.terbit.presentation.ui.components.templates.Onboarding
import com.giftech.terbit.presentation.ui.pages.activity_complete.ActivityCompleteScreen
import com.giftech.terbit.presentation.ui.pages.article.ArticleCompleteScreen
import com.giftech.terbit.presentation.ui.pages.article.ArticleScreen
import com.giftech.terbit.presentation.ui.pages.asaq.complete.WeeklyAsaqCompleteScreen
import com.giftech.terbit.presentation.ui.pages.asaq.prepost.AsaqScreen
import com.giftech.terbit.presentation.ui.pages.asaq.weekly.WeeklyAsaqScreen
import com.giftech.terbit.presentation.ui.pages.dataexport.DataExportScreen
import com.giftech.terbit.presentation.ui.pages.ffq.list.FfqListScreen
import com.giftech.terbit.presentation.ui.pages.ffq.main.FfqMainScreen
import com.giftech.terbit.presentation.ui.pages.ffq.result.FfqResultScreen
import com.giftech.terbit.presentation.ui.pages.graph.GraphScreen
import com.giftech.terbit.presentation.ui.pages.hasil_imt.HasilIMTScreen
import com.giftech.terbit.presentation.ui.pages.hasil_tingkat_pemantauan.HasilTPScreen
import com.giftech.terbit.presentation.ui.pages.home.HomeScreen
import com.giftech.terbit.presentation.ui.pages.input_data_diri.InputDataDiriScreen
import com.giftech.terbit.presentation.ui.pages.monitoring.MonitoringScreen
import com.giftech.terbit.presentation.ui.pages.monitoringdetails.MonitoringDetailsScreen
import com.giftech.terbit.presentation.ui.pages.notificationinbox.NotificationInboxScreen
import com.giftech.terbit.presentation.ui.pages.onboarding.AppOnboardingScreen
import com.giftech.terbit.presentation.ui.pages.onboarding.FfqOnboardingScreen
import com.giftech.terbit.presentation.ui.pages.onboarding_posttest.OnboardingPosttestScreen
import com.giftech.terbit.presentation.ui.pages.profesional.ProfesionalScreen
import com.giftech.terbit.presentation.ui.pages.profile.EditProfileScreen
import com.giftech.terbit.presentation.ui.pages.profile.ProfileScreen
import com.giftech.terbit.presentation.ui.pages.weeklysummary.WeeklySummaryScreen
import com.giftech.terbit.presentation.ui.route.BottomNavItem
import com.giftech.terbit.presentation.ui.route.Screen
import com.giftech.terbit.presentation.util.Constants
import com.giftech.terbit.domain.util.Constants as DomainConstants

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
            composable(Screen.AppOnboarding.route) {
                AppOnboardingScreen(
                    navController = navHostController,
                )
            }
            
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
                        navHostController.navigate(
                            Screen.ASAQ.createRoute(
                                testType = Constants.AsaqTestType.PRE_TEST,
                                programId = DomainConstants.ProgramId.FIRST_ASAQ,
                            )
                        )
                    },
                    hero = HeroEnum.AsaqOnboard3
                )
            }
            
            // ASAQ
            composable(
                route = Screen.ASAQ.route,
                deepLinks = listOf(
                    navDeepLink { uriPattern = Screen.ASAQ.deepLink }
                ),
                arguments = listOf(
                    navArgument(Constants.Extras.TEST_TYPE) { type = NavType.IntType },
                    navArgument(Constants.Extras.PROGRAM_ID) { type = NavType.IntType },
                ),
            ) {
                val testType = it.arguments?.getInt(Constants.Extras.TEST_TYPE) ?: -1
                val programId = it.arguments?.getInt(Constants.Extras.PROGRAM_ID) ?: -1
                
                AsaqScreen(
                    isPreTest = testType == 0,
                    programId = programId,
                    navController = navHostController,
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
                        navHostController.navigate(Screen.Home.route) {
                            popUpTo(Screen.AppOnboarding.route) {
                                inclusive = true
                            }
                        }
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
                    },
                    onExportData = {
                        navHostController.navigate(Screen.DataExport.route)
                    },
                )
            }
            
            composable(Screen.EditProfile.route) {
                EditProfileScreen(
                    onBack = {
                        navHostController.popBackStack()
                    }
                )
            }
            
            composable(Screen.NotificationInbox.route) {
                NotificationInboxScreen(
                    navController = navHostController,
                )
            }
            
            composable(Screen.Profesional.route) {
                ProfesionalScreen(
                    onBack = {
                        navHostController.popBackStack()
                    }
                )
            }
            
            composable(
                route = Screen.MonitoringDetails.route,
                arguments = listOf(
                    navArgument(Constants.Extras.WEEK) { type = NavType.IntType }
                ),
            ) {
                val week = it.arguments?.getInt(Constants.Extras.WEEK) ?: -1
                MonitoringDetailsScreen(
                    week = week,
                    navController = navHostController,
                )
            }
            
            // FFQ Main
            composable(
                route = Screen.FfqMain.route,
                arguments = listOf(
                    navArgument(Constants.Extras.PROGRAM_ID) { type = NavType.IntType }
                ),
            ) {
                val programId = it.arguments?.getInt(Constants.Extras.PROGRAM_ID) ?: -1
                FfqMainScreen(
                    programId = programId,
                    navController = navHostController,
                )
            }
            
            composable(
                route = Screen.FfqList.route,
                arguments = listOf(
                    navArgument(Constants.Extras.PROGRAM_ID) { type = NavType.IntType },
                    navArgument(Constants.Extras.FFQ_FOOD_CATEGORY_ID) { type = NavType.IntType },
                ),
            ) {
                val programId = it.arguments?.getInt(Constants.Extras.PROGRAM_ID) ?: -1
                val foodCategoryId =
                    it.arguments?.getInt(Constants.Extras.FFQ_FOOD_CATEGORY_ID) ?: -1
                FfqListScreen(
                    programId = programId,
                    foodCategoryId = foodCategoryId,
                    navController = navHostController,
                )
            }
            
            composable(
                route = Screen.FfqResult.route,
                arguments = listOf(
                    navArgument(Constants.Extras.PROGRAM_ID) { type = NavType.IntType }
                ),
            ) {
                val programId = it.arguments?.getInt(Constants.Extras.PROGRAM_ID) ?: -1
                FfqResultScreen(
                    programId = programId,
                    navController = navHostController,
                )
            }
            
            composable(
                route = Screen.Article.route,
                arguments = listOf(
                    navArgument(Constants.Extras.PROGRAM_ID) { type = NavType.IntType },
                    navArgument(Constants.Extras.WEEK) { type = NavType.IntType },
                    navArgument(Constants.Extras.DAY) { type = NavType.IntType },
                ),
            ) {
                val programId = it.arguments?.getInt(Constants.Extras.PROGRAM_ID) ?: -1
                val week = it.arguments?.getInt(Constants.Extras.WEEK) ?: -1
                val day = it.arguments?.getInt(Constants.Extras.DAY) ?: -1
                ArticleScreen(
                    programId = programId,
                    week = week,
                    day = day,
                    navController = navHostController,
                )
            }
            
            composable(
                route = Screen.ArticleComplete.route,
                arguments = listOf(
                    navArgument(Constants.Extras.WEEK) { type = NavType.IntType },
                    navArgument(Constants.Extras.DAY) { type = NavType.IntType },
                ),
            ) {
                val week = it.arguments?.getInt(Constants.Extras.WEEK) ?: -1
                val day = it.arguments?.getInt(Constants.Extras.DAY) ?: -1
                ArticleCompleteScreen(
                    week,
                    day,
                    onNext = {
                        navHostController.popBackStack()
                    },
                )
            }
            
            composable(
                route = Screen.ActivityComplete.route,
                arguments = listOf(
                    navArgument(Constants.Extras.WEEK) { type = NavType.IntType },
                ),
            ) {
                val week = it.arguments?.getInt(Constants.Extras.WEEK) ?: -1
                ActivityCompleteScreen(
                    week = week,
                    onNext = {
                        navHostController.popBackStack()
                    }
                )
            }
            
            composable(
                route = Screen.WeeklyAsaq.route,
                deepLinks = listOf(
                    navDeepLink { uriPattern = Screen.WeeklyAsaq.deepLink }
                ),
                arguments = listOf(
                    navArgument(Constants.Extras.PROGRAM_ID) { type = NavType.IntType }
                ),
            ) {
                val programId = it.arguments?.getInt(Constants.Extras.PROGRAM_ID) ?: -1
                WeeklyAsaqScreen(
                    programId = programId,
                    navController = navHostController,
                )
            }
            
            composable(
                route = Screen.WeeklyAsaqComplete.route,
                arguments = listOf(
                    navArgument(Constants.Extras.SEDENTARY_AVERAGE_HOURS) { type = NavType.FloatType }
                ),
            ) {
                val sedentaryAverageHours =
                    it.arguments?.getFloat(Constants.Extras.SEDENTARY_AVERAGE_HOURS) ?: 0f
                WeeklyAsaqCompleteScreen(
                    sedentaryAverageHours = sedentaryAverageHours,
                    navController = navHostController,
                )
            }
            
            composable(Screen.OnboardingPosttest.route) {
                OnboardingPosttestScreen(
                    onNext = {
                        navHostController.navigate(
                            Screen.ASAQ.createRoute(
                                testType = Constants.AsaqTestType.POST_TEST,
                                programId = DomainConstants.ProgramId.LAST_ASAQ,
                            ),
                        )
                    }
                )
            }
            
            composable(Screen.DataExport.route) {
                DataExportScreen(
                    navController = navHostController,
                )
            }
            
            composable(
                route = Screen.WeeklySummary.route,
                arguments = listOf(
                    navArgument(Constants.Extras.WEEK) { type = NavType.IntType }
                ),
            ) {
                val week = it.arguments?.getInt(Constants.Extras.WEEK) ?: -1
                WeeklySummaryScreen(
                    week = week,
                    onNext = {
                        navHostController.navigate(Screen.Home.route) {
                            popUpTo(Screen.WeeklySummary.route) {
                                inclusive = true
                            }
                        }
                    },
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