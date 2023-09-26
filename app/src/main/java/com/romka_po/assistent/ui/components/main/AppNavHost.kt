@file:OptIn(ExperimentalMaterial3Api::class)

package com.romka_po.assistent.ui.components.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
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
    state: BottomSheetScaffoldState,
    height: MutableState<Dp>
) {
    val flag = remember{mutableStateOf(false)}
    NavHost(modifier = modifier.fillMaxSize(), navController = navController, startDestination = Screens.DashBoard.route){
        composable(Screens.DashBoard.route){
            DashboardScreen(state, height)
            flag.value=true
        }
        composable(Screens.Settings.route){
            SettingsScreen()
            flag.value=true
        }
        composable(Screens.Catalog.route){
            CatalogScreen(state, height)
            flag.value=true
        }
        composable(Screens.Chart.route){
            StatsScreen(state, height)
            flag.value=true
        }
    }
}
