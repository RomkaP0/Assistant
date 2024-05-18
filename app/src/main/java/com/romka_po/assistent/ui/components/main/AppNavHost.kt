package com.romka_po.assistent.ui.components.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.romka_po.assistent.model.nav.Screens
import com.romka_po.assistent.ui.screens.auth.AuthScreen
import com.romka_po.assistent.ui.screens.dashboard.DashboardScreen
import com.romka_po.assistent.ui.screens.detail.DetailScreen
import com.romka_po.assistent.ui.screens.health.HealthScreen
import com.romka_po.assistent.ui.screens.register.RegisterScreen
import com.romka_po.assistent.ui.screens.settings.SettingsScreen
import com.romka_po.assistent.ui.screens.stats.StatsScreen
import com.romka_po.assistent.ui.screens.story.StoryScreen

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController,
    startRoute: Screens,
    showNavBarState: MutableState<Boolean>,
) {
    NavHost(modifier = modifier.fillMaxSize(), navController = navController, startDestination = startRoute.route){
        composable(Screens.DashBoard.route){
            showNavBarState.value = true
            DashboardScreen(navController)
        }
        composable(Screens.Settings.route){
            showNavBarState.value = true
            SettingsScreen(navController)
        }
        composable(Screens.Health.route){
            HealthScreen()
        }
        composable(Screens.Chart.route){
            StatsScreen()
        }
        composable(Screens.Story.route){
            showNavBarState.value = false
            StoryScreen(navController)
        }
        composable(Screens.Auth.route){
            showNavBarState.value = false
            AuthScreen(navController)
        }
        composable(Screens.Register.route){
            showNavBarState.value = false
            RegisterScreen(navController)
        }
        composable(Screens.Detail.route){
            showNavBarState.value = false
            DetailScreen(navController)
        }
    }
}
