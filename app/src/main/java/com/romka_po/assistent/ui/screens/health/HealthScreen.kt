package com.romka_po.assistent.ui.screens.health

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.romka_po.assistent.ui.components.stats.HealthProgressBar

@Composable
fun HealthScreen() {
    val viewModel: HealthViewModel = hiltViewModel()

    val viewState by viewModel.viewState.collectAsState(null)
//    when(viewModel.actionState.collectAsState(null).value) {
//        is HealthViewModel.Action.ComponentEditClicked -> TODO()
//        null -> {}
//    }

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
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            MaterialTheme.colorScheme.onSecondary,
                            RoundedCornerShape(8.dp)
                        )
                        .clickable { viewModel.onComponentClicked(it) }
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(
                            text = it.label,
                            style = MaterialTheme.typography.labelLarge,
                            minLines = 2,
                            maxLines = 2,
                        )
                        Text(
                            text = "${it.current}/${it.max}",
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }
                    LinearProgressIndicator(progress = { 1f - it.current.toFloat()/it.max })
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .height(76.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            MaterialTheme.colorScheme.onSecondary,
                            RoundedCornerShape(8.dp),
                        )
                ){
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(
                                text = "Добавить",
                                style = MaterialTheme.typography.labelLarge,
                                minLines = 2,
                                maxLines = 2,
                            )
                            Text(
                                text = "Новый компонент",
                                style = MaterialTheme.typography.labelMedium,
                            )
                        }
                        Icon(modifier = Modifier.weight(1f), imageVector = Icons.Rounded.Add, contentDescription = null)
                    }
                    HorizontalDivider(thickness = 4.dp, color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }

    viewState?.healthData?.let { EditComponentDialog(it, viewModel::onComponentChanged, viewModel::onDialogClose) }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditComponentDialog(healthData: HealthData, onComponentChanged: (HealthData) -> Unit, onDismiss: () -> Unit) {
    BasicAlertDialog(
        modifier = Modifier.fillMaxSize(),
        onDismissRequest = onDismiss,
//        properties = DialogProperties(usePlatformDefaultWidth = false, decorFitsSystemWindows = false),
    ) {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(
                modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Редактирование компонента",
                    modifier = Modifier.padding(vertical = 16.dp),
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(modifier = Modifier.padding(bottom = 8.dp), text = healthData.label, style = MaterialTheme.typography.bodyLarge)
                TextField(
                    label = { Text(text = "Текущее значение")},
                    value = "${healthData.current}",
                    onValueChange = { onComponentChanged.invoke(healthData.copy(current = it.toInt())) },
                    colors = TextFieldDefaults.colors(unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent),
                    shape = RoundedCornerShape(8.dp)
                )
                TextField(
                    label = { Text(text = "Максимальное значение")},
                    value = "${healthData.max}",
                    onValueChange = { onComponentChanged.invoke(healthData.copy(current = it.toInt())) },
                    colors = TextFieldDefaults.colors(unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent),
                    shape = RoundedCornerShape(8.dp),
                )
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .padding(horizontal = 8.dp), horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = onDismiss) {
                        Text(text = "Отменить")
                    }
                    Spacer(modifier = Modifier.width(24.dp))
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Сохранить")
                    }
                }
            }
        }
    }
}


private fun calculateHealth(dataList: List<HealthData>): Int {
    var currentDistance = 0
    var maxDistance = 0

    dataList.forEach {
        currentDistance += it.current
        maxDistance += it.max
    }
    return 100 - currentDistance * 100 / maxDistance
}

val healthDataList = listOf(
    HealthData(0,"Моторное масло", 2563, 7000),
    HealthData(1,"Антифриз", 9830, 60000),
    HealthData(2,"Колодки тормозные", 3589, 30000),
    HealthData(3,"Фильтр топливный", 1234, 30000),
    HealthData(4,"Фильтр воздушный", 17854, 30000),
    HealthData(5,"Свечи зажигания", 7629,40000),
    HealthData(6,"Аккумулятор", 7658,40000),
    HealthData(7,"Цепь ГРМ", 74082,100000),
    HealthData(8,"Тормозная жидкость", 11573, 30000)
)

data class HealthData(
    val id: Int,
    val label: String,
    val current: Int,
    val max: Int,
)