@file:OptIn(ExperimentalMaterial3Api::class)

package com.romka_po.assistent.ui.screens.main

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.romka_po.assistent.model.nav.Screens
import com.romka_po.assistent.model.theme.TypeTheme
import com.romka_po.assistent.ui.components.main.AppNavHost
import com.romka_po.assistent.ui.theme.AssistentTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val screens =
        listOf(Screens.DashBoard, Screens.Health, Screens.Chart, Screens.Settings)
    private val viewModel: MainViewModel by viewModels()

    private val currentTheme = mutableStateOf(TypeTheme.AUTO)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.POST_NOTIFICATIONS,
            ),
            111
        )
        requestLocationPermission()

        lifecycleScope.launch {
            viewModel.currentTheme.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .distinctUntilChanged().collect {
                    currentTheme.value = it
                }
        }

//        MapKitFactory.initialize(this)

        setContent {

            val height = remember { mutableStateOf(BottomSheetDefaults.SheetPeekHeight) }
            val navController = rememberNavController()
            val showNavBarState = remember {
                mutableStateOf(true)
            }
//            mapState = rememberMapViewWithLifecycle()
//            if (!(::userLocationLayer.isInitialized && userLocationLayer.isValid)) {
//                val mapKit = MapKitFactory.getInstance()
//
//                userLocationLayer = mapKit.createUserLocationLayer(mapState.mapWindow).apply {
//                    isVisible = true
//                    isHeadingEnabled = true
//                    isAutoZoomEnabled = true
//                    setObjectListener(this@MainActivity)
//
//                }
//            }
            val startRoute = when {
                viewModel.isFirstOpen.value -> Screens.Story
                else -> Screens.DashBoard
            }

            AssistentTheme(
                darkTheme = when (currentTheme.value) {
                    TypeTheme.LIGHT -> false
                    TypeTheme.DARK -> true
                    TypeTheme.AUTO -> isSystemInDarkTheme()
                }
            ) {
                // A surface container using the 'background' color from the theme
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                ) {
                    val scope = this
                    height.value = scope.maxHeight.value.dp
                    AppNavHost(
                        modifier = Modifier.fillMaxSize(),
                        navController = navController,
                        startRoute = startRoute,
                        showNavBarState = showNavBarState
                    )
                    if (showNavBarState.value) {
//                        NavBar(
//                            Modifier
//                                .align(Alignment.BottomCenter)
//                                .padding(20.dp)
//                                .clip(
//                                    RoundedCornerShape(16.dp)
//                                )
//                        ) {
//                            val navBackStackEntry by navController.currentBackStackEntryAsState()
//                            val currentDestination = navBackStackEntry?.destination
//                            screens.forEach { screen ->
////                                NavigationBarItem(
////                                    selected = currentDestination?.route == screen.route,
////                                    label = { Text(text = stringResource(id = screen.stringId)) },
////                                    icon = {
////                                        Icon(
////                                            modifier = Modifier.size(48.dp),
////                                            imageVector = ImageVector.vectorResource(id = screen.drawableId!!),
////                                            contentDescription = null
////                                        )
////                                    },
////                                    onClick = {
////                                        navController.navigate(screen.route) {
////                                            // Pop up to the start destination of the graph to
////                                            // avoid building up a large stack of destinations
////                                            // on the back stack as users select items
////                                            popUpTo(navController.graph.findStartDestination().id) {
////                                                saveState = true
////                                            }
////                                            // Avoid multiple copies of the same destination when
////                                            // reselecting the same item
////                                            launchSingleTop = true
////                                            // Restore state when reselecting a previously selected item
////                                            restoreState = true
////                                        }
////                                    })
//                                if (currentDestination?.route == screen.route) {
//                                    Row(
//                                        modifier = Modifier
//                                            .height(48.dp)
//                                            .clip(RoundedCornerShape(16.dp))
//
//                                            .background(
//                                                Color.Gray
//                                            )
//                                            .clickable {
//                                                navController.navigate(screen.route) {
//                                                    // Pop up to the start destination of the graph to
//                                                    // avoid building up a large stack of destinations
//                                                    // on the back stack as users select items
//                                                    popUpTo(navController.graph.findStartDestination().id) {
//                                                        saveState = true
//                                                    }
//                                                    // Avoid multiple copies of the same destination when
//                                                    // reselecting the same item
//                                                    launchSingleTop = true
//                                                    // Restore state when reselecting a previously selected item
//                                                    restoreState = true
//                                                }
//                                            }
//                                            .padding(12.dp)
//
//                                    ) {
//                                        Icon(
//                                            modifier = Modifier
//                                                .size(24.dp),
//                                            imageVector = ImageVector.vectorResource(id = screen.drawableId!!),
//                                            contentDescription = null
//                                        )
//                                        Text(text = stringResource(id = screen.stringId))
//                                    }
//                                } else {
//                                    Icon(
//                                        modifier = Modifier
//                                            .size(24.dp)
//                                            .clickable {
//                                                navController.navigate(screen.route) {
//                                                    // Pop up to the start destination of the graph to
//                                                    // avoid building up a large stack of destinations
//                                                    // on the back stack as users select items
//                                                    popUpTo(navController.graph.findStartDestination().id) {
//                                                        saveState = true
//                                                    }
//                                                    // Avoid multiple copies of the same destination when
//                                                    // reselecting the same item
//                                                    launchSingleTop = true
//                                                    // Restore state when reselecting a previously selected item
//                                                    restoreState = true
//                                                }
//                                            },
//                                        imageVector = ImageVector.vectorResource(id = screen.drawableId!!),
//                                        contentDescription = null
//                                    )
//                                }
//                            }
//                        }
                        NavigationBar(
                            Modifier
                                .align(Alignment.BottomCenter)
                                .padding(20.dp)
                                .clip(
                                    RoundedCornerShape(16.dp)
                                )
                        ) {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            screens.forEach { screen ->
                                NavigationBarItem(
                                    selected = currentDestination?.route == screen.route,
                                    label = { Text(text = stringResource(id = screen.stringId))},
                                    alwaysShowLabel = false,
                                    icon = {
                                        Icon(
                                            modifier = Modifier.size(24.dp),
                                            imageVector = ImageVector.vectorResource(id = screen.drawableId!!),
                                            contentDescription = null
                                        )
                                    },
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
                                            restoreState = false
                                        }
                                    })
                            }
                        }
                    }
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

