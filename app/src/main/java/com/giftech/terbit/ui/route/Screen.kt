package com.giftech.terbit.ui.route

import com.giftech.terbit.ui.utils.Constants

sealed class Screen(val route: String, val deepLink: String? = null) {
    
    object InputDataDiri : Screen("InputDataDiri")
    object OnboardingIMT : Screen("OnboardingIMT")
    object HasilIMT : Screen("HasilIMT")
    object OnboardingASAQ1 : Screen("OnboardingASAQ1")
    object OnboardingASAQ2 : Screen("OnboardingASAQ2")
    object OnboardingASAQ3 : Screen("OnboardingASAQ3")
    
    object ASAQ : Screen(
        route = "ASAQ/{${Constants.Extras.TEST_TYPE}}/{${Constants.Extras.PROGRAM_ID}}",
        deepLink = "https://terbiasafit.com/program/preposttest_asaq/{${Constants.Extras.TEST_TYPE}}/{${Constants.Extras.PROGRAM_ID}}",
    ) {
        fun createRoute(testType: Int, programId: Int) = "ASAQ/$testType/$programId"
        
        fun createDeepLink(testType: Int, programId: Int) =
            "https://terbiasafit.com/program/preposttest_asaq/$testType/$programId"
        
    }
    
    // FFQ
    object FfqOnboarding : Screen("FfqOnboarding")
    
    object FfqMain : Screen("FfqMain/{${Constants.Extras.PROGRAM_ID}}") {
        fun createRoute(programId: Int) = "FfqMain/${programId}"
    }
    
    object FfqList :
        Screen("FfqList/{${Constants.Extras.PROGRAM_ID}}/{${Constants.Extras.FFQ_FOOD_CATEGORY_ID}}") {
        fun createRoute(programId: Int, foodCategoryId: Int) =
            "FfqList/${programId}/${foodCategoryId}"
    }
    
    object FfqResult : Screen("FfqResult/{${Constants.Extras.PROGRAM_ID}}") {
        fun createRoute(programId: Int) = "FfqResult/${programId}"
    }
    
    object OnboardingTingkatPemantauan : Screen("OnboardingTingkatPemantauan")
    
    object HasilTingkatPemantauan : Screen("HasilTingkatPemantauan")
    
    object Home : Screen("Home")
    
    object Monitoring : Screen("Monitoring")
    
    object Graph : Screen("Graph")
    
    object Profile : Screen("Profile")
    object EditProfile : Screen("EditProfile")
    
    object NotificationInbox : Screen("NotificationInbox")
    
    object Profesional : Screen("Profesional")
    
    object MonitoringDetails : Screen("MonitoringDetails/{${Constants.Extras.WEEK}}") {
        fun createRoute(week: Int) = "MonitoringDetails/$week"
    }
    
    object Article : Screen(
        "Article/{${Constants.Extras.PROGRAM_ID}}/{${Constants.Extras.WEEK}}/{${Constants.Extras.DAY}}"
    ) {
        fun createRoute(programId: Int, week: Int, day: Int) = "Article/$programId/$week/$day"
    }
    
    object ArticleComplete :
        Screen("ArticleComplete/{${Constants.Extras.WEEK}}/{${Constants.Extras.DAY}}") {
        fun createRoute(week: Int, day: Int) = "ArticleComplete/$week/$day"
    }
    
    object ActivityComplete : Screen("ActivityComplete/{${Constants.Extras.WEEK}}") {
        fun createRoute(week: Int) = "ActivityComplete/$week"
    }
    
    
    object WeeklyAsaq : Screen(
        route = "WeeklyAsaq/{${Constants.Extras.PROGRAM_ID}}",
        deepLink = "https://terbiasafit.com/program/weekly_asaq/{${Constants.Extras.PROGRAM_ID}}"
    ) {
        fun createRoute(programId: Int) = "WeeklyAsaq/$programId"
        fun createDeepLink(programId: Int) =
            "https://terbiasafit.com/program/weekly_asaq/$programId"
    }
    
    object WeeklyAsaqComplete : Screen("WeeklyAsaqComplete")
    object OnboardingPosttest : Screen("OnboardingPosttest")

}