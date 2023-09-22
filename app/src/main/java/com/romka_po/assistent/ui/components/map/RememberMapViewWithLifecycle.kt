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
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationManager
import com.yandex.mapkit.mapview.MapView

private var myLocationListener: LocationListener? = null
private var locationManager: LocationManager? = null
private var myLocation: Point? = null
val COMFORTABLE_ZOOM_LEVEL = 18F
private val DESIRED_ACCURACY = 0.0
private val MINIMAL_TIME: Long = 0
private val MINIMAL_DISTANCE = 50.0
private val USE_IN_BACKGROUND = false
lateinit var mapView: MapView

@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    // on below line initializing
    // our maps view with id.

    mapView = remember {
        MapView(context)
    }

//    locationManager = MapKitFactory.getInstance().createLocationManager();
//    myLocationListener = object : LocationListener {
//        override fun onLocationUpdated(location: Location) {
//            if (myLocation == null) {
//                moveCamera(location.getPosition(), COMFORTABLE_ZOOM_LEVEL);
//            }
//            myLocation = location.getPosition();
//            Log.w(
//                ContentValues.TAG,
//                "my location - " + myLocation!!.latitude + "," + myLocation!!.longitude
//            );
//            mapView.moveToUser()
//        }
//
//        override fun onLocationStatusUpdated(locationStatus: LocationStatus) {
//            if (locationStatus == LocationStatus.NOT_AVAILABLE) {
//                Toast.makeText(context, "Not Availeerf", Toast.LENGTH_LONG).show();
//            }
//        }
//
//    }

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
//                    subscribeToLocationUpdate();

                    Log.d("FEFEF", "FEFEFE")
                }

                Lifecycle.Event.ON_PAUSE -> {
                    mapView.onStop()
                    MapKitFactory.getInstance().onStop()
//                    myLocationListener?.let { locationManager?.unsubscribe(it) };
                    Log.d("WEEWEWEWE", "WEWEWEWEW")
                }

                else -> {}
            }
        }
    }

//private fun subscribeToLocationUpdate() {
//    if (locationManager != null && myLocationListener != null) {
//        locationManager!!.subscribeForLocationUpdates(
//            DESIRED_ACCURACY,
//            MINIMAL_TIME,
//            MINIMAL_DISTANCE,
//            USE_IN_BACKGROUND,
//            FilteringMode.OFF,
//            myLocationListener!!
//        )
//    }
//}
//
//private fun moveCamera(point: Point, zoom: Float) {
//    mapView.map.move(
//        CameraPosition(point, zoom, 0.0f, 0.0f),
//        Animation(Animation.Type.SMOOTH, 1F),
//        null
//    )
//}
//
//fun MapView.moveToUser() {
//    if (myLocation == null) {
//        Log.i(TAG, "moveToUser: ")
//    } else {
//        Log.d(TAG, "${myLocation!!.latitude} ${myLocation!!.longitude}")
//        moveCamera(myLocation!!, COMFORTABLE_ZOOM_LEVEL);
//
//    }
//}