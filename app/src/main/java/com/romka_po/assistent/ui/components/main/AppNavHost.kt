@file:OptIn(ExperimentalMaterial3Api::class)

package com.romka_po.assistent.ui.components.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.romka_po.assistent.model.nav.Screens
import com.romka_po.assistent.ui.screens.catalog.CatalogScreen
import com.romka_po.assistent.ui.screens.dashboard.DashboardScreen
import com.romka_po.assistent.ui.screens.settings.SettingsScreen
import com.romka_po.assistent.ui.screens.stats.StatsScreen

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController,
    state: BottomSheetScaffoldState
) {
    NavHost(modifier = modifier.fillMaxSize(), navController = navController, startDestination = Screens.DashBoard.route){
        composable(Screens.DashBoard.route){
            DashboardScreen(state)
        }
        composable(Screens.Settings.route){
            SettingsScreen()
        }
        composable(Screens.Catalog.route){
            CatalogScreen(state)
        }
        composable(Screens.Chart.route){
            StatsScreen(state)
        }
    }
}