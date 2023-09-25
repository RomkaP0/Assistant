@file:OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.romka_po.assistent.ui.screens.dashboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.romka_po.assistent.databinding.VkmapBinding
import com.romka_po.assistent.ui.components.shared.HorizontalBottomCard
import com.romka_po.assistent.ui.components.shared.TopWithBottomCard
import com.romka_po.assistent.ui.components.shared.VerticalBottomCard


@Composable
fun DashboardScreen(state: BottomSheetScaffoldState) {
//    val state = rememberBottomSheetScaffoldState(SheetState(true, LocalDensity.current))

    TopWithBottomCard(state = state, content = {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            AndroidViewBinding(factory = VkmapBinding::inflate){

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
            HorizontalBottomCard(color = Color.Yellow) {
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
