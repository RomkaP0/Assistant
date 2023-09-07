package com.romka_po.assistent.presentation.screens.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.yandex.mapkit.mapview.MapView

@Composable
fun DashboardScreen(mapState: MapView) {
//    val map = MapKitFactory.getInstance().createUserLocationLayer(mapState.mapWindow)
//    map.isVisible = true
//    MapKitFactory.getInstance().createLocationManager().subscribeForLocationUpdates(0.0,0, 0.0, true, FilteringMode.ON, object :LocationListener{
//        override fun onLocationUpdated(location: Location) {
//            Log.d("TagCheck", "LocationUpdated " + location.position.longitude);
//            Log.d("TagCheck", "LocationUpdated " + location.position.latitude);
//        }
//
//        override fun onLocationStatusUpdated(p0: LocationStatus) {
//        }
//
//    })
    Column {
        AndroidView(modifier = Modifier.fillMaxHeight(0.4f).padding(16.dp).clip(RoundedCornerShape(16.dp)), factory = { mapState }) { mapView ->
//            mapView.map.move(
//                CameraPosition()
//            )
        }
//        LazyVerticalGrid()
    }
}