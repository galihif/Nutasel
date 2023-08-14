package com.giftech.terbit.ui.route

sealed class Screen(val route:String){
    object InputDataDiri:Screen("InputDataDiri")
    object OnboardingIMT:Screen("OnboardingIMT")
    object HasilIMT:Screen("HasilIMT")
    object OnboardingStatusGizi:Screen("OnboardingStatusGizi")
    object HasilStatusGizi:Screen("HasilStatusGizi")
    object OnboardingASAQ1:Screen("OnboardingASAQ1")
    object OnboardingASAQ2:Screen("OnboardingASAQ2")
    object OnboardingASAQ3:Screen("OnboardingASAQ3")
    object  ASAQ:Screen("ASAQ")
    object OnboardingTingkatPemantauan:Screen("OnboardingTingkatPemantauan")
    object HasilTingkatPemantauan:Screen("HasilTingkatPemantauan")
}
