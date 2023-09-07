package com.romka_po.assistent.ui.components.map

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.mapview.MapView

@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    // on below line initializing
    // our maps view with id.

    val mapView = remember {
        MapView(context)
    }
//    val userLocation = remember {
//        MapKitFactory.getInstance().createUserLocationLayer(mapView.mapWindow)
//    }
//
    val maf = MapKitFactory.getInstance().createUserLocationLayer(mapView.mapWindow)
    maf.isVisible = true
    maf.cameraPosition()
//    // Makes MapView follow the lifecycle of this composable
    val lifecycleObserver = rememberMapLifecycleObserver(mapView)
//
//    // on below line initializing lifecycle variable.
    val lifecycle = LocalLifecycleOwner.current.lifecycle
//
    // on below line adding observer for lifecycle.
    DisposableEffect(lifecycle) {
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }
    // returning map view on below line.
    return mapView
}

// creating a function for map lifecycle observer.
@Composable
fun rememberMapLifecycleObserver(mapView: MapView): LifecycleEventObserver =
    remember(mapView) {
        // on below line adding different events for maps view
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> {
                    MapKitFactory.getInstance().onStart()
                    mapView.onStart()
                    Log.d("FEFEF", "FEFEFE")
                }

                Lifecycle.Event.ON_STOP -> {
                    mapView.onStop()
                    MapKitFactory.getInstance().onStop()
                    Log.d("WEEWEWEWE", "WEWEWEWEW")
                }

                else -> {}
            }
        }
    }
