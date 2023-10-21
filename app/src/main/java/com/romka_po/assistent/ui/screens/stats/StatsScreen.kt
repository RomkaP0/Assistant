package com.romka_po.assistent.ui.screens.stats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carlosgub.kotlinm.charts.ChartAnimation
import com.carlosgub.kotlinm.charts.line.LineChart
import com.carlosgub.kotlinm.charts.line.LineChartData
import com.carlosgub.kotlinm.charts.line.LineChartPoint
import com.carlosgub.kotlinm.charts.line.LineChartSeries
import kotlin.random.Random

@Composable
fun StatsScreen(){
    val lineData = remember {
        LineChartData(
            series = (1..3).map {
                LineChartSeries(
                    dataName = "data $it",
                    lineColor = listOf(
                        Color.Yellow,
                        Color.Red,
                        Color.Blue,
                    )[it - 1],
                    listOfPoints = (1..10).map { point ->
                        LineChartPoint(
                            x = Random.nextLong(1000),
                            y = (1..15).random().toFloat(),
                        )
                    }
                )
            },
        )
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LineChart(
            lineChartData = lineData,
            modifier = Modifier.height(300.dp),
            xAxisLabel = {
                Text(
                    fontSize = 12.sp,
                    text = "4",
                    textAlign = TextAlign.Center
                )
            },
            overlayHeaderLabel = {
                Text(
                    text = "2",
                    style = MaterialTheme.typography.bodySmall
                )
            },
            animation = ChartAnimation.Sequenced()
        )
    }
}