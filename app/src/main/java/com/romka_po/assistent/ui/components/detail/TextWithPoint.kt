package com.romka_po.assistent.ui.components.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun TextWithPoint(modifier: Modifier = Modifier, text: String = "") {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier
            .padding(8.dp)
            .size(4.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(MaterialTheme.colorScheme.primary))
        Text(text = text, style = MaterialTheme.typography.bodyMedium)
    }
}