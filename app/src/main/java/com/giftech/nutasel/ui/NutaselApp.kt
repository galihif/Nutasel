package com.giftech.nutasel.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.giftech.nutasel.ui.pages.InputDataDiriScreen
import com.giftech.nutasel.ui.route.Screen

@ExperimentalMaterial3Api
@Composable
fun NutaselApp() {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = Screen.InputDataDiri.route
    ) {
        composable(Screen.InputDataDiri.route){
            InputDataDiriScreen()
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun PreviewNutaselApp() {
    NutaselApp()
}