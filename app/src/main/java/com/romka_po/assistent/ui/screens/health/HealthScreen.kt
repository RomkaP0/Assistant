package com.romka_po.assistent.ui.screens.health

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ElectricalServices
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.romka_po.assistent.ui.components.stats.HealthProgressBar

@Composable
fun HealthScreen() {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp), horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                HealthProgressBar(canvasSize = 150.dp, indicatorValue = calculateHealth(healthDataList))
                Text(
                    modifier = Modifier.offset(y= (-14).dp),
                    text = "Состояние: Хорошее",
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(healthDataList) {
                Column(modifier = Modifier.background(
                    MaterialTheme.colorScheme.onSecondary,
                    RoundedCornerShape(8.dp)
                )) {
                    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {

                        Column(modifier = Modifier) {
                            Text(
                                text = it.label,
                                style = MaterialTheme.typography.labelLarge,
                                minLines = 2,
                                maxLines = 2,
                            )
                            Text(text = "${it.current}/${it.max}", style = MaterialTheme.typography.labelMedium)
                        }
                    }
                    LinearProgressIndicator(progress = { 1f - it.current.toFloat()/it.max })
                }
            }
        }
    }
}

private fun calculateHealth(dataList: List<HealthData>): Int {
    val currentDistance = mutableIntStateOf(0)
    val maxDistance = mutableIntStateOf(0)

    dataList.forEach {
        currentDistance.intValue += it.current
        maxDistance.intValue += it.max
    }
    return 100 - currentDistance.intValue * 100 / maxDistance.intValue
}

val healthDataList = listOf(
    HealthData("Моторное масло", 2563, 7000),
    HealthData("Антифриз", 9830, 60000),
    HealthData("Колодки тормозные", 3589, 30000),
    HealthData("Фильтр топливный", 1234, 30000),
    HealthData("Фильтр воздушный", 17854, 30000),
    HealthData("Свечи зажигания", 7629,40000),
    HealthData("Аккумулятор", 7658,40000),
    HealthData("Цепь ГРМ", 74082,100000),
    HealthData("Тормозная жидкость", 11573, 30000)
)

data class HealthData(
    val label: String,
    val current: Int,
    val max: Int,
)