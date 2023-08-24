package com.giftech.terbit.ui.route

import com.giftech.terbit.ui.utils.Constants

sealed class Screen(val route: String) {
    
    object InputDataDiri : Screen("InputDataDiri")
    object OnboardingIMT : Screen("OnboardingIMT")
    object HasilIMT : Screen("HasilIMT")
    object OnboardingStatusGizi : Screen("OnboardingStatusGizi")
    object HasilStatusGizi : Screen("HasilStatusGizi")
    object OnboardingASAQ1 : Screen("OnboardingASAQ1")
    object OnboardingASAQ2 : Screen("OnboardingASAQ2")
    object OnboardingASAQ3 : Screen("OnboardingASAQ3")
    object ASAQ1 : Screen("ASAQ1")
    
    // FFQ
    object FfqOnboarding : Screen("FfqOnboarding")
    
    object FfqMain : Screen("FfqMain/{${Constants.EXTRAS.PROGRAM_ID}}") {
        fun createRoute(programId: Int) = "FfqMain/${programId}"
    }
    
    object FfqList : Screen("FfqList/{${Constants.EXTRAS.PROGRAM_ID}}/{${Constants.EXTRAS.FFQ_FOOD_CATEGORY_ID}}") {
        fun createRoute(programId: Int, foodCategoryId: Int) = "FfqList/${programId}/${foodCategoryId}"
    }
    
    object FfqResult : Screen("FfqResult/{${Constants.EXTRAS.PROGRAM_ID}}") {
        fun createRoute(programId: Int) = "FfqResult/${programId}"
    }
    
}