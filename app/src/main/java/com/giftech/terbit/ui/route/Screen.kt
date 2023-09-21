package com.giftech.terbit.ui.route

import com.giftech.terbit.ui.utils.Constants

sealed class Screen(val route: String, val deepLink: String? = null) {
    
    object InputDataDiri : Screen("InputDataDiri")
    object OnboardingIMT : Screen("OnboardingIMT")
    object HasilIMT : Screen("HasilIMT")
    object OnboardingASAQ1 : Screen("OnboardingASAQ1")
    object OnboardingASAQ2 : Screen("OnboardingASAQ2")
    object OnboardingASAQ3 : Screen("OnboardingASAQ3")
    object ASAQ : Screen("ASAQ/{${Constants.EXTRAS.TEST_TYPE}}") {
        fun createRoute(testType: Int) = "ASAQ/$testType"
    }
    
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
    
    object MonitoringDetails : Screen("MonitoringDetails/{${Constants.EXTRAS.WEEK}}") {
        fun createRoute(week: Int) = "MonitoringDetails/$week"
    }
    
    object Article : Screen(
        "Article/{${Constants.EXTRAS.PROGRAM_ID}}/{${Constants.EXTRAS.WEEK}}/{${Constants.EXTRAS.DAY}}"
    ) {
        fun createRoute(programId: Int, week: Int, day: Int) = "Article/$programId/$week/$day"
    }
    
    object ArticleComplete :
        Screen("ArticleComplete/{${Constants.EXTRAS.WEEK}}/{${Constants.EXTRAS.DAY}}") {
        fun createRoute(week: Int, day: Int) = "ArticleComplete/$week/$day"
    }
    
    object ActivityComplete : Screen("ActivityComplete/{${Constants.EXTRAS.WEEK}}") {
        fun createRoute(week: Int) = "ActivityComplete/$week"
    }
    
    
    object WeeklyAsaq : Screen(
        route = "WeeklyAsaq/{${Constants.EXTRAS.PROGRAM_ID}}",
        deepLink = "https://terbiasafit.com/program/weekly_asaq/{${Constants.EXTRAS.PROGRAM_ID}}"
    ) {
        fun createRoute(programId: Int) = "WeeklyAsaq/$programId"
        fun createDeepLink(programId: Int) = "https://terbiasafit.com/program/weekly_asaq/$programId"
    }
    
    object WeeklyAsaqComplete : Screen("WeeklyAsaqComplete")
    
}