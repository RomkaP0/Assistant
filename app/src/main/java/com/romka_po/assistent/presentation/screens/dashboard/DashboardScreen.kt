package com.romka_po.assistent.presentation.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.mapview.MapView

@Composable
fun DashboardScreen(mapState: MapView) {
    val context = LocalContext.current

//    val map = mapState.mapWindow.map

//    val imageProvider = ImageProvider.fromResource(context, R.drawable.baseline_architecture_24)
//    val placemarkObject = map.mapObjects.addPlacemark(Point(55.751280, 37.629720), imageProvider)
//    placemarkObject.addTapListener(placemarkTapListener)




    Column {
        AndroidView(modifier = Modifier
            .fillMaxHeight(0.4f)
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp)), factory = { mapState }) { mapView ->
//            mapView.map.move(
//                CameraPosition()
//            )
//            mapView.map.

        }
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
            ,
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items((0..30).toList()) {
                Column(modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.primary), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = it.toString())
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }
    }
}