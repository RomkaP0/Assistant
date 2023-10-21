@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.romka_po.assistent.ui.components.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TopWithBottomCard(
    modifier: Modifier,
    state: BottomSheetScaffoldState,
    height: MutableState<Dp>,
    content: @Composable () -> Unit,
    sheetContent: @Composable () -> Unit,
) {
    val contentHeight = height.value * 0.35f
    val sheetHeight = height.value * 0.6f
    Box(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        BottomSheetScaffold(
            modifier = modifier
                .fillMaxWidth()
                .height(contentHeight)
                .padding(horizontal = 8.dp),
            scaffoldState = state,
            sheetPeekHeight = sheetHeight,
            //            sheetTonalElevation = 10.dp,
            sheetShadowElevation = 80.dp,
            sheetContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            sheetShape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),

            content = {
                content()
            },

            sheetContent = {
                sheetContent()
            }
        )
    }

}
