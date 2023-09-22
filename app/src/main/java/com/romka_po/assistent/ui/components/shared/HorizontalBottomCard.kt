package com.romka_po.assistent.ui.components.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalBottomCard(
    modifier: Modifier = Modifier,
    padding: Int = 16,
    corner: Int = 16,
    color: Color = MaterialTheme.colorScheme.surface,
    content: @Composable () -> Unit,
    ) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = padding.dp)
            .clip(RoundedCornerShape(corner.dp))
            .background(color, RoundedCornerShape(corner.dp))
            .padding(padding.dp),
        horizontalArrangement = Arrangement.spacedBy(padding.dp)
    ) {
        content()
    }
}