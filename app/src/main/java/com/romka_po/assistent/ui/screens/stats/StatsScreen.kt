@file:OptIn(ExperimentalKoalaPlotApi::class, ExperimentalMaterial3Api::class)

package com.romka_po.assistent.ui.screens.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import io.github.koalaplot.core.ChartLayout
import io.github.koalaplot.core.bar.DefaultVerticalBarPlotEntry
import io.github.koalaplot.core.bar.DefaultVerticalBarPosition
import io.github.koalaplot.core.bar.VerticalBarPlot
import io.github.koalaplot.core.bar.VerticalBarPlotEntry
import io.github.koalaplot.core.util.ExperimentalKoalaPlotApi
import io.github.koalaplot.core.xygraph.XYGraph
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

private val YAxisRange = 0f..500f
private val YAxisRange2 = 0f..100f
private val XAxisRange = 2f..33f

internal val fibonacci = mutableStateListOf(100f, 100f, 235f, 347f, 487f, 21f)
internal val avgSpeed = mutableStateListOf(68f, 53f, 41f, 48f, 37f, 71f)

private fun barChartEntries(): List<VerticalBarPlotEntry<Float, Float>> {
    return buildList {
        fibonacci.forEachIndexed { index, fl ->
            add(
                DefaultVerticalBarPlotEntry(
                    x = (index + 1)*5.toFloat(),
                    y = DefaultVerticalBarPosition(
                        yMin = 0f,
                        yMax = fl,
                    )
                )
            )
        }
    }
}
private fun barChartEntries2(): List<VerticalBarPlotEntry<Float, Float>> {
    return buildList {
        avgSpeed.forEachIndexed { index, fl ->
            add(
                DefaultVerticalBarPlotEntry(
                    x = (index + 1)*5.toFloat(),
                    y = DefaultVerticalBarPosition(
                        yMin = 0f,
                        yMax = fl,
                    )
                )
            )
        }
    }
}


@Preview
@Composable
fun StatsScreen() {

    var chooseDateTimestamp by remember{ mutableLongStateOf(Clock.System.now().toEpochMilliseconds()) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = chooseDateTimestamp
    )
    var openDateDialog by remember { mutableStateOf(false) }
    var state by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PrimaryTabRow(
            modifier = Modifier.padding(horizontal = 20.dp),
            selectedTabIndex = state,
            divider = {}
        ) {
            Periods.entries.forEachIndexed { index, period ->
                Tab(
                    modifier = Modifier.clip(RoundedCornerShape(8.dp)),
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(text = period.s, maxLines = 2, overflow = TextOverflow.Ellipsis) }
                )
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Icon(
                modifier = Modifier.padding(horizontal = 8.dp),
                imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                contentDescription = null
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val localDate = Instant.fromEpochMilliseconds(chooseDateTimestamp)
                    .toLocalDateTime(TimeZone.currentSystemDefault())
                Row(modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .clickable {
                        datePickerState.selectedDateMillis = chooseDateTimestamp
                        openDateDialog = true

                    }) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = when (state) {
                            Periods.DAY.ordinal -> "${localDate.dayOfWeek.toString().substring(0, 2)}, ${localDate.dayOfMonth} ${localDate.month.toString().substring(0, 3)}".lowercase()
                            Periods.WEEK.ordinal -> "${Instant.fromEpochMilliseconds(datePickerState.selectedDateMillis!! - 604800000).toLocalDateTime(
                                TimeZone.currentSystemDefault()).dayOfMonth} ${Instant.fromEpochMilliseconds(datePickerState.selectedDateMillis!! - 604800000).toLocalDateTime(
                                TimeZone.currentSystemDefault()).month.toString().substring(0,3)} - ${localDate.dayOfMonth} ${localDate.month.toString().substring(0, 3)}".lowercase()
//                            Periods.MONTH.ordinal -> localDate.month.toString().substring(0, 3).lowercase()
                            Periods.MONTH.ordinal -> "Апрель"
                            Periods.YEAR.ordinal -> localDate.year.toString()
                            else -> ""
                        }
                    )
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                }
//                Text(text = "25 ноября")
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "км", color = Color.Transparent)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "1369",
                        style = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "км", style = TextStyle(fontSize = 16.sp))
                }
            }
            Icon(
                modifier = Modifier.padding(horizontal = 8.dp),
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = null
            )
        }
        ElevatedCard(modifier = Modifier.padding(horizontal = 8.dp), shape = RoundedCornerShape(16.dp)) {
            ChartLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.6f)
                    .padding(horizontal = 4.dp).padding(top = 16.dp, bottom = 4.dp)
            ) {
                XYGraph(
                    xAxisModel = io.github.koalaplot.core.xygraph.LinearAxisModel(
                        XAxisRange,
                        minimumMajorTickIncrement = 5f,
                        minimumMajorTickSpacing = 4.dp,
                        zoomRangeLimit = 3f,
                        minorTickCount = 0
                    ),
                    xAxisLabels = { x -> "${x.toInt()}" },
                    xAxisTitle = "День",
                    yAxisModel = io.github.koalaplot.core.xygraph.LinearAxisModel(
                        YAxisRange,
                        minimumMajorTickIncrement = 1f,
                        zoomRangeLimit = 3f,
                        minorTickCount = 0,
                        minimumMajorTickSpacing = 40.dp
                    ),
                    yAxisTitle = "Расстояние, км",
                    content = {
                        VerticalBarPlot(
                            data = barChartEntries(),
                            bar = {
                                Box(
                                    modifier = Modifier
                                        .width(50.dp)
                                        .height(200.dp)
                                        .background(
                                            MaterialTheme.colorScheme.primary.copy(0.8f),
                                            RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)
                                        )
                                )
                            },
                        )
                    }
                )
            }
        }

        ElevatedCard(modifier = Modifier.padding(horizontal = 8.dp), shape = RoundedCornerShape(16.dp)) {
            ChartLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.6f)
                    .padding(horizontal = 4.dp).padding(top = 16.dp, bottom = 4.dp)
            ) {
                XYGraph(
                    xAxisModel = io.github.koalaplot.core.xygraph.LinearAxisModel(
                        XAxisRange,
                        minimumMajorTickIncrement = 5f,
                        minimumMajorTickSpacing = 4.dp,
                        zoomRangeLimit = 3f,
                        minorTickCount = 0
                    ),
                    xAxisLabels = { x -> "${x.toInt()}" },
                    xAxisTitle = "День",
                    yAxisModel = io.github.koalaplot.core.xygraph.LinearAxisModel(
                        YAxisRange2,
                        minimumMajorTickIncrement = 1f,
                        zoomRangeLimit = 3f,
                        minorTickCount = 0,
                        minimumMajorTickSpacing = 40.dp
                    ),
                    yAxisTitle = "Скорость, км/ч",
                    content = {
                        VerticalBarPlot(
                            data = barChartEntries2(),
                            bar = {
                                Box(
                                    modifier = Modifier
                                        .width(50.dp)
                                        .height(200.dp)
                                        .background(
                                            MaterialTheme.colorScheme.primary.copy(0.8f),
                                            RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)
                                        )
                                )
                            },
                        )
                    }
                )
            }
        }
        
//        HorizontalDivider(modifier = Modifier
//            .fillMaxWidth(), thickness = 20.dp)
//        Text(text = "Средняя скорость")
        if (openDateDialog) {
            val currentTime = Clock.System.now()

            DatePickerDialog(
                onDismissRequest = { openDateDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        chooseDateTimestamp = datePickerState.selectedDateMillis!!
                        openDateDialog = false
                    }) {
                        Text(text = "Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { openDateDialog = false }) {
                        Text(text = "Dismiss")
                    }
                },
                properties = DialogProperties()

            ) {
                val dateFormatter: DatePickerFormatter =
                    remember { DatePickerDefaults.dateFormatter() }
                DatePicker(state = datePickerState,
                    showModeToggle = false,
                    headline = {
                        Row(
                            Modifier
                                .height(56.dp)
                                .padding(
                                    PaddingValues(
                                        start = 24.dp,
                                        end = 12.dp
                                    )
                                ),
                        ) {
                            DatePickerDefaults.DatePickerHeadline(
                                selectedDateMillis = datePickerState.selectedDateMillis,
                                displayMode = datePickerState.displayMode,
                                dateFormatter = dateFormatter
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable {
                                        datePickerState.selectedDateMillis =
                                            currentTime.toEpochMilliseconds()
                                        datePickerState.displayedMonthMillis =
                                            currentTime.toEpochMilliseconds()
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .size(28.dp),
                                    imageVector = Icons.Default.CalendarToday,
                                    contentDescription = null
                                )
                                Text(
                                    modifier = Modifier.padding(top = 4.dp),
                                    text = currentTime.toLocalDateTime(TimeZone.currentSystemDefault()).dayOfMonth.toString(),
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight(500)
                                    )
                                )
                            }

                        }
                    }
                )
            }
        }
    }
}