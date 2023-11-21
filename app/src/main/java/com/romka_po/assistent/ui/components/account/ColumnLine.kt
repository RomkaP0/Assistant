package com.romka_po.assistent.ui.components.account

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ColumnLine(text: String, icon: ImageVector, red: Boolean, clickFunc: () -> Unit){
    val color = Color(0xFFF75555)
    Row(modifier = Modifier.clickable { clickFunc() }, verticalAlignment = Alignment.CenterVertically) {
        Icon(modifier = Modifier.size(28.dp), imageVector = icon, contentDescription = null, tint = if (red) color else LocalContentColor.current)
        Text(modifier = Modifier.padding(start = 20.dp), text = text, color = if (red) color else Color.Unspecified)
    }
}