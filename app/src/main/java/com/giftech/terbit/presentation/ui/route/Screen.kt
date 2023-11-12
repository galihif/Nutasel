package com.giftech.terbit.presentation.ui.route

import com.giftech.terbit.presentation.util.Constants

sealed class Screen(val route: String, val deepLink: String? = null) {
    
    data object AppOnboarding : Screen("AppOnboarding")
    
    data object InputDataDiri : Screen("InputDataDiri")
    
    data object OnboardingIMT : Screen("OnboardingIMT")
    
    data object HasilIMT : Screen("HasilIMT")
    
    data object OnboardingASAQ1 : Screen("OnboardingASAQ1")
    
    data object OnboardingASAQ2 : Screen("OnboardingASAQ2")
    
    data object OnboardingASAQ3 : Screen("OnboardingASAQ3")
    
    data object ASAQ : Screen(
        route = "ASAQ/{${Constants.Extras.TEST_TYPE}}/{${Constants.Extras.PROGRAM_ID}}",
        deepLink = "https://terbiasafit.com/program/preposttest_asaq/{${Constants.Extras.TEST_TYPE}}/{${Constants.Extras.PROGRAM_ID}}",
    ) {
        fun createRoute(testType: Int, programId: Int) = "ASAQ/$testType/$programId"
        
        fun createDeepLink(testType: Int, programId: Int) =
            "https://terbiasafit.com/program/preposttest_asaq/$testType/$programId"
        
    }
    
    // FFQ
    data object FfqOnboarding : Screen("FfqOnboarding")
    
    data object FfqMain : Screen("FfqMain/{${Constants.Extras.PROGRAM_ID}}") {
        fun createRoute(programId: Int) = "FfqMain/${programId}"
    }
    
    data object FfqList :
        Screen("FfqList/{${Constants.Extras.PROGRAM_ID}}/{${Constants.Extras.FFQ_FOOD_CATEGORY_ID}}") {
        fun createRoute(programId: Int, foodCategoryId: Int) =
            "FfqList/${programId}/${foodCategoryId}"
    }
    
    data object FfqResult : Screen("FfqResult/{${Constants.Extras.PROGRAM_ID}}") {
        fun createRoute(programId: Int) = "FfqResult/${programId}"
    }
    
    data object OnboardingTingkatPemantauan : Screen("OnboardingTingkatPemantauan")
    
    data object HasilTingkatPemantauan : Screen("HasilTingkatPemantauan")
    
    data object Home : Screen("Home")
    
    data object Monitoring : Screen("Monitoring")
    
    data object Graph : Screen("Graph")
    
    data object Profile : Screen("Profile")
    
    data object EditProfile : Screen("EditProfile")
    
    data object NotificationInbox : Screen("NotificationInbox")
    
    data object Profesional : Screen("Profesional")
    
    data object MonitoringDetails : Screen("MonitoringDetails/{${Constants.Extras.WEEK}}") {
        fun createRoute(week: Int) = "MonitoringDetails/$week"
    }
    
    data object Article : Screen(
        "Article/{${Constants.Extras.PROGRAM_ID}}/{${Constants.Extras.WEEK}}/{${Constants.Extras.DAY}}"
    ) {
        fun createRoute(programId: Int, week: Int, day: Int) = "Article/$programId/$week/$day"
    }
    
    data object ArticleComplete :
        Screen("ArticleComplete/{${Constants.Extras.WEEK}}/{${Constants.Extras.DAY}}") {
        fun createRoute(week: Int, day: Int) = "ArticleComplete/$week/$day"
    }
    
    data object ActivityComplete : Screen("ActivityComplete/{${Constants.Extras.WEEK}}") {
        fun createRoute(week: Int) = "ActivityComplete/$week"
    }
    
    
    data object WeeklyAsaq : Screen(
        route = "WeeklyAsaq/{${Constants.Extras.PROGRAM_ID}}",
        deepLink = "https://terbiasafit.com/program/weekly_asaq/{${Constants.Extras.PROGRAM_ID}}"
    ) {
        fun createRoute(programId: Int) = "WeeklyAsaq/$programId"
        fun createDeepLink(programId: Int) =
            "https://terbiasafit.com/program/weekly_asaq/$programId"
    }
    
    data object WeeklyAsaqComplete : Screen("WeeklyAsaqComplete/{${Constants.Extras.SEDENTARY_AVERAGE_HOURS}}") {
        fun createRoute(sedentaryAverageHours: Float) = "WeeklyAsaqComplete/$sedentaryAverageHours"
    }
    
    data object OnboardingPosttest : Screen("OnboardingPosttest")
    
    data object DataExport : Screen("DataExport")

    data object WeeklySummary : Screen("WeeklySummary/{${Constants.Extras.WEEK}}") {
        fun createRoute(week: Int) = "WeeklySummary/${week}"
    }
    
}