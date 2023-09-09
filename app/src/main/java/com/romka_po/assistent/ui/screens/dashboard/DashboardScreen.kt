package com.romka_po.assistent.ui.screens.dashboard

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
    Column {
        AndroidView(modifier = Modifier.fillMaxHeight(0.4f).padding(16.dp).clip(RoundedCornerShape(16.dp)), factory = { mapState }) { mapView ->

        }
    }
}