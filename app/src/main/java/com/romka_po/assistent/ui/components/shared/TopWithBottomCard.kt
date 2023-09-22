@file:OptIn(ExperimentalFoundationApi::class)

package com.romka_po.assistent.ui.components.shared

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopWithBottomCard(
    topContent: @Composable () -> Unit,
    cardContent: @Composable () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxSize()) {

        topContent()
        Column(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .align(Alignment.BottomStart)
                .padding(top = 8.dp)
                .drawColoredShadow(Color.Black, alpha = 0.3f, shadowRadius = 40.dp, offsetY = 8.dp)
                .background(
                    MaterialTheme.colorScheme.surface,
                    RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                )
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(
                modifier = Modifier
                    .width(48.dp)
                    .clip(RoundedCornerShape(2.dp)),
                thickness = 4.dp,
                color = Color.Black.copy(0.1f)
            )
            cardContent()
        }
    }
}
