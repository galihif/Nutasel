package com.giftech.nutasel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.giftech.nutasel.ui.NutaselApp
import com.giftech.nutasel.ui.theme.NutaselTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NutaselTheme {
                NutaselApp()
            }
        }
    }
}