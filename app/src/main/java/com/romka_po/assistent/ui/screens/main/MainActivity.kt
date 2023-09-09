package com.romka_po.assistent.ui.screens.main

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.romka_po.assistent.model.nav.Screens
import com.romka_po.assistent.ui.components.main.AppNavHost
import com.romka_po.assistent.ui.components.map.rememberMapViewWithLifecycle
import com.romka_po.assistent.ui.theme.AssistentTheme
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.user_location.UserLocationLayer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var userLocation:UserLocationLayer
    private val screens = listOf(Screens.DashBoard, Screens.Settings)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestLocationPermission()



        MapKitFactory.initialize(this)

        setContent {
            val navController = rememberNavController()
            val mapState = rememberMapViewWithLifecycle()
            val m = MapKitFactory.getInstance()
            if (!::userLocation.isInitialized) {
                userLocation = m.createUserLocationLayer(mapState.mapWindow)
                userLocation.cameraPosition()
                userLocation.isVisible = true
                userLocation.isAutoZoomEnabled = true
                userLocation.isHeadingEnabled = true
            }
            AssistentTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,
                    bottomBar = {
                        NavigationBar() {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            screens.forEach { screen ->
                                NavigationBarItem(
                                    selected = currentDestination?.route == screen.route,
                                    icon = {
                                        Icon(
                                            modifier = Modifier.size(24.dp),
                                            imageVector = ImageVector.vectorResource(id = screen.drawableId),
                                            contentDescription = null
                                        )
                                    },
                                    label = { Text(stringResource(id = screen.stringId)) },
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            // Pop up to the start destination of the graph to
                                            // avoid building up a large stack of destinations
                                            // on the back stack as users select items
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            // Avoid multiple copies of the same destination when
                                            // reselecting the same item
                                            launchSingleTop = true
                                            // Restore state when reselecting a previously selected item
                                            restoreState = true
                                        }
                                    })
                            }
                        }
                    }
                ) { padding ->
                    AppNavHost(modifier = Modifier.padding(padding), navController, mapState)
                }
            }

        }
    }

    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                "android.permission.ACCESS_FINE_LOCATION"
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf("android.permission.ACCESS_FINE_LOCATION"),
                1
            )
        }
    }
}

