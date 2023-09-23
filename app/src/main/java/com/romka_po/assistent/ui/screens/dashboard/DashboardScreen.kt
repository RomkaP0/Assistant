@file:OptIn(ExperimentalFoundationApi::class)

package com.romka_po.assistent.ui.screens.dashboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.romka_po.assistent.ui.components.shared.HorizontalBottomCard
import com.romka_po.assistent.ui.components.shared.TopWithBottomCard
import com.romka_po.assistent.ui.components.shared.drawColoredShadow
import com.yandex.mapkit.mapview.MapView


@Composable
fun DashboardScreen(mapState: MapView) {

        TopWithBottomCard(topContent = {
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.4f)
                    .height((0.4f).toInt().dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, Color.Black.copy(0.2f), RoundedCornerShape(16.dp))
                    .shadow(16.dp, clip = false)
            ) {
                AndroidView(factory = { mapState }) { mapView ->
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(36.dp)
                        .offset(x = (-4).dp, y = (-28).dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
                        .clickable {

                        }
                ) {
                    Icon(
                        modifier = Modifier.align(Alignment.Center),
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null
                    )
                }
            }
        }) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val cardModifier =
                    Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Green.copy(0.4f))
                        .weight(1f, true)
                        .padding(8.dp)

                Column(modifier = cardModifier) {
                    Text(text = "Distance", style = MaterialTheme.typography.titleLarge)
                    Text(text = "543535")
                }
                Column(modifier = cardModifier) {
                    Text(text = "States", style = MaterialTheme.typography.titleLarge)
                    Text(text = "Good")
                }
            }
            HorizontalBottomCard(color = Color.Yellow) {
                Icon(imageVector = Icons.Default.WorkspacePremium, contentDescription = null)
                Text(
                    modifier = Modifier,
                    text = "Приобрести Премиум - доступ",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.height(1000.dp))
        }
    }


@Preview(showBackground = true)
@Composable()
fun checl() {
    Box(
        modifier = Modifier
            .padding(30.dp)
            .drawColoredShadow(
                Color.Black,
                alpha = 0.7f,
                shadowRadius = 80.dp,
                borderRadius = 80.dp
            )
            .size(300.dp)
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun check() {
    Box(
        modifier = Modifier
            .padding(30.dp)
            .drawColoredShadow(Color.Black, alpha = 0.7f, shadowRadius = 90.dp)
            .size(300.dp)
    ) {

    }
}
