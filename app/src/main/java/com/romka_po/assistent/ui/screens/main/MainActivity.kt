package com.romka_po.assistent.ui.screens.main

import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
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
import com.romka_po.assistent.R
import com.romka_po.assistent.model.nav.Screens
import com.romka_po.assistent.model.theme.TypeTheme
import com.romka_po.assistent.ui.components.main.AppNavHost
import com.romka_po.assistent.ui.components.map.rememberMapViewWithLifecycle
import com.romka_po.assistent.ui.theme.AssistentTheme
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.map.CompositeIcon
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.map.RotationType
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import com.yandex.runtime.image.ImageProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity(), UserLocationObjectListener {

    private lateinit var userLocationLayer: UserLocationLayer
    private lateinit var mapState: MapView


    private val screens = listOf(Screens.DashBoard, Screens.Catalog, Screens.Chart, Screens.Settings)
    private val viewModel: MainViewModel by viewModels()

    private val currentTheme = mutableStateOf(TypeTheme.AUTO)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestLocationPermission()

        lifecycleScope.launch {
            viewModel.currentTheme.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .distinctUntilChanged().collect {
                    currentTheme.value = it
            }
        }

        MapKitFactory.initialize(this)

        setContent() {
            val navController = rememberNavController()
            mapState = rememberMapViewWithLifecycle()
            if (!(::userLocationLayer.isInitialized && userLocationLayer.isValid)) {
                val mapKit = MapKitFactory.getInstance()

                userLocationLayer = mapKit.createUserLocationLayer(mapState.mapWindow).apply {
                    isVisible = true
                    isHeadingEnabled = true
                    isAutoZoomEnabled = true
                    setObjectListener(this@MainActivity)

                }
            }

            AssistentTheme(darkTheme = when (currentTheme.value){
                TypeTheme.LIGHT -> false
                TypeTheme.DARK -> true
                TypeTheme.AUTO -> isSystemInDarkTheme()
            }) {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,
                    bottomBar = {
                        NavigationBar {
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

    override fun onObjectAdded(userLocationView: UserLocationView) {
        userLocationLayer.setAnchor(
            PointF((mapState.width * 0.5).toFloat(), (mapState.height * 0.5).toFloat()),
            PointF((mapState.width * 0.5).toFloat(), (mapState.height * 0.83).toFloat())
        )

        userLocationView.arrow.setIcon(
            ImageProvider.fromResource(
                this, R.drawable.car
            )

        )

        val pinIcon: CompositeIcon = userLocationView.pin.useCompositeIcon()

        pinIcon.setIcon(
            "icon",
            ImageProvider.fromResource(this, R.drawable.new_moon),
            IconStyle().setAnchor(PointF(0f, 0f))
                .setRotationType(RotationType.NO_ROTATION)
                .setZIndex(0f)
                .setScale(1f)
        )

        pinIcon.setIcon(
            "pin",
            ImageProvider.fromResource(this, R.drawable.new_moon),
            IconStyle().setAnchor(PointF(0.5f, 0.5f))
                .setRotationType(RotationType.ROTATE)
                .setZIndex(1f)
                .setScale(0.5f)
        )

        userLocationView.accuracyCircle.fillColor = Color.BLUE and -0x66000001
    }

    override fun onObjectRemoved(p0: UserLocationView) {
        Log.i("onObjectRemoved", "onObjectRemoved")
    }

    override fun onObjectUpdated(p0: UserLocationView, p1: ObjectEvent) {
        Log.i("onObjectUpdated", "onObjectUpdated")
    }

}

