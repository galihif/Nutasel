package com.giftech.terbit.presentation.ui.components.molecules

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.giftech.terbit.presentation.ui.route.BottomNavItem

@Composable
fun BottomNavigation(
    navController: NavHostController,
    navigationItems: List<BottomNavItem>,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp,
        modifier = modifier,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        
        navigationItems.forEach { item ->
            AddItem(
                item = item,
                currentRoute = currentRoute,
                navController = navController,
            )
        }
    }
}

@Composable
private fun RowScope.AddItem(
    item: BottomNavItem,
    currentRoute: String?,
    navController: NavHostController,
) {
    NavigationBarItem(
        label = {
            Text(
                text = item.title,
                style = MaterialTheme.typography.labelMedium,
            )
        },
        icon = {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
            )
        },
        selected = currentRoute == item.screen.route,
        alwaysShowLabel = false,
        onClick = {
            navController.navigate(item.screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                restoreState = true
                launchSingleTop = true
            }
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            indicatorColor = MaterialTheme.colorScheme.primaryContainer,
            unselectedIconColor = MaterialTheme.colorScheme.onSurface,
        ),
    )
}