package com.romka_po.assistent.ui.components.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@Composable
fun CardRadioButton(
    icon: ImageVector,
    brand: String,
    position: Int,
    currentAuthSDK: MutableIntState,
    shapePosition: PositionInColumn = PositionInColumn.TOP
) {
    val shape = when (shapePosition){
        PositionInColumn.TOP -> RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp, bottomStart = 10.dp, bottomEnd = 10.dp)
        PositionInColumn.MIDDLE -> RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp, bottomStart = 10.dp, bottomEnd = 10.dp)
        PositionInColumn.BOTTOM -> RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp, bottomStart = 40.dp, bottomEnd = 40.dp)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(MaterialTheme.colorScheme.onSecondary)
            .padding(24.dp)
            .height(56.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background, RoundedCornerShape(16.dp))) {
            Icon(
                modifier = Modifier
                    .padding(16.dp)
                    .aspectRatio(1f),
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(0.7f)
            )
        }
        Column(
            modifier = Modifier.width(118.dp),
        ) {
//            Text(text = "FEFE")
//            Spacer(modifier = Modifier.height(4.dp))
            Text(text = brand, color = MaterialTheme.colorScheme.onSurface.copy(0.8f)
            )
        }
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background, RoundedCornerShape(16.dp))) {
            RadioButton(modifier = Modifier
                .padding(16.dp)
                .size(24.dp),
                selected = position == currentAuthSDK.intValue, onClick = { currentAuthSDK.intValue = position })
        }
    }
}