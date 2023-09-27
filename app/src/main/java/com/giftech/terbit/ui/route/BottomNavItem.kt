package com.giftech.terbit.ui.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MonitorHeart
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen,
) {
    
    data object Home : BottomNavItem(
        title = "Beranda",
        icon = Icons.Outlined.Home,
        screen = Screen.Home,
    )
    
    data object Monitoring : BottomNavItem(
        title = "Pantau",
        icon = Icons.Outlined.MonitorHeart,
        screen = Screen.Monitoring,
    )
    
    data object Graph : BottomNavItem(
        title = "Grafik",
        icon = Icons.Outlined.BarChart,
        screen = Screen.Graph,
    )
    
    data object Profile : BottomNavItem(
        title = "Profil",
        icon = Icons.Outlined.Person,
        screen = Screen.Profile,
    )
    
}