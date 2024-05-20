package com.romka_po.assistent.ui.components.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun VerticalBottomCard(
    modifier: Modifier = Modifier,
    padding: Int = 16,
    color: CardColors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.onSecondary),
    clickFunc: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.onSecondary)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(CardDefaults.elevatedShape)
                .clickable(enabled = clickFunc != null, onClick = { clickFunc?.let { it() } })
                .padding(padding.dp)
        ) {
            content()
        }
    }
}