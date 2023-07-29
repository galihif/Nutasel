package com.giftech.terbit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.giftech.terbit.ui.TerbitApp
import com.giftech.terbit.ui.theme.TerbitTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TerbitTheme {
                TerbitApp()
            }
        }
    }
}