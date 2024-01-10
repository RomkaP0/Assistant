package com.romka_po.assistent.ui.screens.health

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ElectricalServices
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.romka_po.assistent.ui.components.stats.HealthProgressBar

@Composable
fun HealthScreen() {
    Column {
        HealthProgressBar(canvasSize = 200.dp)
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(12) {
                Column(Modifier.padding(8.dp)) {
                    Row {
                        Icon(
                            modifier = Modifier
                                .padding(bottom = 4.dp, end = 4.dp)
                                .size(48.dp)
                                .background(
                                    MaterialTheme.colorScheme.primaryContainer,
                                    RoundedCornerShape(8.dp)
                                ),
                            imageVector = Icons.Default.ElectricalServices,
                            contentDescription = null
                        )
                        Text(text = "Engine")
                    }
                    LinearProgressIndicator(progress = { 0.7f })
                }
            }
        }
    }
}