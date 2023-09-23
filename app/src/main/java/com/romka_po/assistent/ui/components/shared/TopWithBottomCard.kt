@file:OptIn(ExperimentalMaterial3Api::class)

package com.romka_po.assistent.ui.components.shared

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp

@Composable
fun TopWithBottomCard(
    content: @Composable () -> Unit,
    sheetContent: @Composable () -> Unit,
) {
    BoxWithConstraints {
        val constraintsScope = this
        val maxHeight =
            constraintsScope.maxHeight

        val peekHeight = remember { mutableStateOf((maxHeight.value * 0.6).dp) }
        BottomSheetScaffold(
            sheetPeekHeight = peekHeight.value,
//            sheetTonalElevation = 10.dp,
            sheetShadowElevation = 80.dp,
            sheetContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            sheetShape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),

            content = { content() },

            sheetContent = {
                sheetContent()
            }
        )
    }
}
