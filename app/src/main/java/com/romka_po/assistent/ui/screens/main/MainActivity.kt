package com.romka_po.assistent.ui.screens.main

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.romka_po.assistent.model.nav.Screens
import com.romka_po.assistent.model.theme.TypeTheme
import com.romka_po.assistent.ui.components.main.AppNavHost
import com.romka_po.assistent.ui.theme.AssistentTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val screens =
        listOf(Screens.DashBoard, Screens.Health, Screens.Chart, Screens.Settings)
    private val viewModel: MainViewModel by viewModels()

    private var showUI by mutableStateOf(false)

    private lateinit var isFirstLaunch: MutableState<Boolean>
    private lateinit var startScreen:Screens

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener {
            lifecycleScope.launch {
                viewModel.isFirstOpen.collectLatest {
                    if (it != null) {
                        startScreen = when (it) {
                            true -> Screens.Story
                            else -> Screens.DashBoard
                        }
                        isFirstLaunch = mutableStateOf(it)
                        showUI = true
                    }
                }
            }
            this@MainActivity::isFirstLaunch.isInitialized
        }

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


//        MapKitFactory.initialize(this)

        setContent {

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
            if (showUI) {
                AssistentTheme(
                    colorScheme = viewModel.currentColor.collectAsState().value,
                    darkTheme = when (viewModel.currentTheme.collectAsState().value) {
                        TypeTheme.LIGHT -> false
                        TypeTheme.DARK -> true
                        TypeTheme.AUTO, null -> isSystemInDarkTheme()
                    }
                ) {
                    Surface(color = MaterialTheme.colorScheme.background) {
                        AppNavHost(
                            modifier = Modifier.fillMaxSize(),
                            navController = navController,
                            startRoute = startScreen,
                            showNavBarState = showNavBarState
                        )
                        Box(modifier = Modifier.fillMaxSize()) {
                            if (showNavBarState.value) {
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
                                            label = { Text(text = stringResource(id = screen.stringId)) },
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

