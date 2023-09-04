package com.giftech.terbit.ui.route

import com.giftech.terbit.ui.utils.Constants

sealed class Screen(val route: String) {
    
    object InputDataDiri : Screen("InputDataDiri")
    object OnboardingIMT : Screen("OnboardingIMT")
    object HasilIMT : Screen("HasilIMT")
    object OnboardingASAQ1 : Screen("OnboardingASAQ1")
    object OnboardingASAQ2 : Screen("OnboardingASAQ2")
    object OnboardingASAQ3 : Screen("OnboardingASAQ3")
    object ASAQ : Screen("ASAQ")
    
    // FFQ
    object FfqOnboarding : Screen("FfqOnboarding")
    
    object FfqMain : Screen("FfqMain/{${Constants.EXTRAS.PROGRAM_ID}}") {
        fun createRoute(programId: Int) = "FfqMain/${programId}"
    }
    
    object FfqList :
        Screen("FfqList/{${Constants.EXTRAS.PROGRAM_ID}}/{${Constants.EXTRAS.FFQ_FOOD_CATEGORY_ID}}") {
        fun createRoute(programId: Int, foodCategoryId: Int) =
            "FfqList/${programId}/${foodCategoryId}"
    }
    
    object FfqResult : Screen("FfqResult/{${Constants.EXTRAS.PROGRAM_ID}}") {
        fun createRoute(programId: Int) = "FfqResult/${programId}"
    }
    
    object OnboardingTingkatPemantauan : Screen("OnboardingTingkatPemantauan")
    
    object HasilTingkatPemantauan : Screen("HasilTingkatPemantauan")
    
    object Home : Screen("Home")
    
    object Monitoring : Screen("Monitoring")
    
    object Graph : Screen("Graph")
    
    object Profile : Screen("Profile")
    object EditProfile : Screen("EditProfile")
    
    object NotificationList : Screen("NotificationList")
    
    object Profesional : Screen("Profesional")
    
    object MonitoringDetails : Screen("MonitoringDetails")
    
}