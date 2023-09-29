@file:OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.romka_po.assistent.ui.screens.dashboard

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.romka_po.assistent.databinding.VkmapBinding
import com.romka_po.assistent.domain.location.LocationService
import com.romka_po.assistent.ui.components.shared.HorizontalBottomCard
import com.romka_po.assistent.ui.components.shared.TopWithBottomCard
import com.romka_po.assistent.ui.components.shared.VerticalBottomCard


@Composable
fun DashboardScreen(state: BottomSheetScaffoldState, height: MutableState<Dp>) {
    TopWithBottomCard(Modifier, state = state, height, content = {
        AndroidViewBinding(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp)),
            factory = VkmapBinding::inflate


        ) {
            this.mapView.getMapAsync {
                it.setLocationSource()
            }
        }
    }) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top)

        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                VerticalBottomCard(modifier = Modifier.weight(1f), color = Color.Unspecified) {
                    Text(text = "Distance", style = MaterialTheme.typography.displaySmall)
                    Text(text = "543535")
                }
                VerticalBottomCard(modifier = Modifier.weight(1f), color = Color.Green) {
                    Text(text = "States", style = MaterialTheme.typography.displaySmall)
                    Text(text = "Good")
                }
            }
            val context = LocalContext.current
            HorizontalBottomCard(modifier = Modifier.clickable {
                Intent(context, LocationService::class.java).apply {
                    action = LocationService.ACTION_START
                    context.startService(this)
                }
            }, color = Color.Yellow) {
                Icon(
                    imageVector = Icons.Default.WorkspacePremium,
                    contentDescription = null
                )
                Text(
                    modifier = Modifier,
                    text = "Приобрести Премиум - доступ",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.height(1000.dp))
        }
    }
}
