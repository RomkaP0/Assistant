package com.romka_po.assistent.presentation.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.romka_po.assistent.R

@Composable
fun SettingsScreen() {
    Column {
        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.1f)) {
            Image(modifier = Modifier.align(Alignment.BottomStart), painter = painterResource(id = R.drawable.img008___0004), contentDescription = null)
            Text(modifier = Modifier.align(Alignment.TopEnd),text = "Роман Полянский")
            Text(modifier = Modifier.align(Alignment.BottomEnd),text = "Изменить данные >")
        }
    }
}