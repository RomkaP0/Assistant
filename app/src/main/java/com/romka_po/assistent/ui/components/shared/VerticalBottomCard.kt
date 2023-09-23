package com.romka_po.assistent.ui.components.shared

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun VerticalBottomCard(
    modifier: Modifier = Modifier,
    padding: Int = 16,
    corner: Int = 16,
    color: Color = MaterialTheme.colorScheme.surface,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(corner.dp))
            .padding(1.dp)
            .border(2.dp, color.copy(0.2f), RoundedCornerShape(corner.dp))
            .padding(padding.dp)
    ) {
        content()
    }
}