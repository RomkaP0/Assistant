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
import com.romka_po.assistent.ui.screens.auth.AuthScreen
import com.romka_po.assistent.ui.screens.catalog.CatalogScreen
import com.romka_po.assistent.ui.screens.dashboard.DashboardScreen
import com.romka_po.assistent.ui.screens.detail.DetailScreen
import com.romka_po.assistent.ui.screens.settings.SettingsScreen
import com.romka_po.assistent.ui.screens.stats.StatsScreen
import com.romka_po.assistent.ui.screens.story.StoryScreen

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController,
    state: BottomSheetScaffoldState,
    height: MutableState<Dp>,
    startRoute: Screens,
    showNavBarState: MutableState<Boolean>,
) {
    val flag = remember{mutableStateOf(false)}
    NavHost(modifier = modifier.fillMaxSize(), navController = navController, startDestination = startRoute.route){
        composable(Screens.DashBoard.route){
            showNavBarState.value = true
            DashboardScreen(state, height)
            flag.value=true
        }
        composable(Screens.Settings.route){
            SettingsScreen(state, height)
            flag.value=true
        }
        composable(Screens.Catalog.route){
            CatalogScreen(state, height, navController)
            flag.value=true
        }
        composable(Screens.Chart.route){
            StatsScreen()
            flag.value=true
        }
        composable(Screens.Story.route){
            showNavBarState.value = false
            StoryScreen(navController)
        }
        composable(Screens.Auth.route){
            showNavBarState.value = false
            AuthScreen(navController)
        }
        composable(Screens.Detail.route){
            showNavBarState.value = false
            DetailScreen(navController, state = state, height = height)
        }

    }
}
