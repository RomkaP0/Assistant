package com.romka_po.assistent.ui.components.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.romka_po.assistent.ui.screens.dashboard.DashboardScreen
import com.yandex.mapkit.mapview.MapView

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController,
    mapState: MapView
) {
    NavHost(modifier = modifier, navController = navController, startDestination = "DASHBOARD"){
        composable("DASHBOARD"){
            DashboardScreen(mapState)
        }
    }
}