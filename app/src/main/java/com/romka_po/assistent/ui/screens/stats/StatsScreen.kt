package com.romka_po.assistent.ui.screens.stats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.carlosgub.kotlinm.charts.ChartAnimation
import com.carlosgub.kotlinm.charts.bar.BarChart
import com.carlosgub.kotlinm.charts.bar.BarChartCategory
import com.carlosgub.kotlinm.charts.bar.BarChartConfig
import com.carlosgub.kotlinm.charts.bar.BarChartData
import com.carlosgub.kotlinm.charts.bar.BarChartEntry

@Composable
fun StatsScreen(){
    val barChartData = BarChartData(
        categories = listOf(
            BarChartCategory(
                name = "Bar Chart 1",
                entries = listOf(
                    BarChartEntry(
                        x = "primary",
                        y = 17f,
                        color = Color.Yellow,
                    ),
                    BarChartEntry(
                        x = "secondary",
                        y = 30f,
                        color = Color.Red,
                    ),
                )
            ),

        )
    )
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        BarChart(
            data = barChartData,
            config = BarChartConfig(
                thickness = 14.dp,
                cornerRadius = 7.dp,
            ),
            modifier = Modifier.height(500.dp),
            animation = ChartAnimation.Sequenced(),
        )
    }
}