@file:OptIn(ExperimentalMaterial3Api::class)

package com.romka_po.assistent.ui.screens.stats

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.romka_po.assistent.ui.components.detail.TextWithPoint
import com.romka_po.assistent.ui.components.shared.TopWithBottomCard

val blocks = listOf(
    "Общая информация",
    "Безопасность",
    "Размеры",
    "Объём и масса",
    "Трансмиссия",
    "Подвеска и тормоза",
    "Эксплуатационные показатели",
    "Двигатель",
    "Аккумуляторная батарея"
)

@Preview(showBackground = true)
@Composable
fun StatsScreen() {
    val viewModel: StatsViewModel = hiltViewModel()

    val selectedCard = viewModel.selectedCard.collectAsState()

    TopWithBottomCard(content = { /*TODO*/ }) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(
                (-32).dp
            )
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            blocks.forEachIndexed { index, str ->
                Brush.verticalGradient()
                Column(
                    modifier = Modifier
                        .animateContentSize()
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                        .border(
                            2.dp,
                            Brush.linearGradient(
                                0.98f to Color.Black,
                                0.98f to Color.Transparent,
                                start = Offset(0.0f, 0.0f),
                                end = Offset(0.0f, Float.POSITIVE_INFINITY),
                            ),
                            RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                        )
                        .clickable(remember { MutableInteractionSource() }, indication = null) {
                            viewModel.pickCard(index)
                        }
                ) {
                    Text(
                        modifier = Modifier.padding(start = 32.dp, top = 20.dp, bottom = 20.dp),
                        text = str,
                        style = MaterialTheme.typography.titleMedium
                    )
                    if (index == selectedCard.value) {
                        blocks.forEach {
                            TextWithPoint(modifier = Modifier.padding(start = 40.dp), text = it)
                        }
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}