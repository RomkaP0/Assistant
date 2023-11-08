@file:OptIn(ExperimentalMaterial3Api::class)

package com.romka_po.assistent.ui.screens.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
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

@Composable
fun DetailScreen(
    navController: NavHostController,
    state: BottomSheetScaffoldState,
    height: MutableState<Dp>
) {
    val viewModel: DetailViewModel = hiltViewModel()

    val animTime = 400

    val selectedCard = viewModel.selectedCard.collectAsState()

    TopWithBottomCard(Modifier, state, height, content = {
//        AsyncImage(model = , contentDescription = )
        Box {
            FloatingActionButton(modifier = Modifier, onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = null)
            }
        }

    }) {
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
                        .animateContentSize(tween(animTime))
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
                    AnimatedVisibility(
                        visible = index == selectedCard.value,
                        enter = expandVertically(tween(animTime), expandFrom = Alignment.Top),
                        exit = shrinkVertically(tween(animTime), shrinkTowards = Alignment.Top)
                    )
                    {
                        Column(modifier = Modifier.padding(start = 40.dp)) {
                            blocks.forEach {
                                TextWithPoint( text = it)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(100.dp)
                    .border(
                        2.dp,
                        Brush.linearGradient(
                            0f to Color.Black,
                            0.01f to Color.Transparent,
                            0.99f to Color.Transparent,
                            1f to Color.Black,
                            start = Offset(0.0f, 0.0f),
                            end = Offset(Float.POSITIVE_INFINITY, 0.0f),
                        ),
                        RectangleShape
                    )
            )
        }
    }
}