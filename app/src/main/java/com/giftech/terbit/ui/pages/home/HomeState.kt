package com.giftech.terbit.ui.pages.home

data class HomeState(
    val userName: String = "",
    val monitoringLevel: String = "",
    val bmiCategory: String = "",
    val bmiValue: Float = 0.0f,
)