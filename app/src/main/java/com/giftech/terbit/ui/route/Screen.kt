package com.giftech.terbit.ui.route

sealed class Screen(val route:String){
    object InputDataDiri:Screen("InputDataDiri")
    object OnboardingASAQ1:Screen("OnboardingASAQ1")
    object OnboardingASAQ2:Screen("OnboardingASAQ2")
    object OnboardingASAQ3:Screen("OnboardingASAQ3")
    object  ASAQ1:Screen("ASAQ1")
}
