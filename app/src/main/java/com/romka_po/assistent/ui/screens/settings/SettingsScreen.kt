@file:OptIn(ExperimentalMaterial3Api::class)

package com.romka_po.assistent.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.romka_po.assistent.model.theme.TypeTheme
import com.romka_po.assistent.ui.components.account.ColumnLine

@Composable
fun SettingsScreen() {
    val typesTheme = TypeTheme.entries.toTypedArray()

    val viewModel: SettingsViewModel = hiltViewModel()

    val currentTheme = viewModel.currentTheme.collectAsState()

    val colorList = listOf(Color.Magenta, Color.Gray, Color.Red, Color.Blue, Color.Green, Color.Yellow).map { it.copy(alpha = 0.4f) }

    var isSheetOpen by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),

        ) {
        ColumnLine(text = "Оформление", icon = Icons.Outlined.DarkMode, red = false) {
            isSheetOpen = true
        }
        ColumnLine(text = "Разрешения", icon = Icons.Outlined.Shield, red = false) {

        }
    }
    if (isSheetOpen) {
        ModalBottomSheet(onDismissRequest = { isSheetOpen = false }, tonalElevation = 0.dp) {
            Column(Modifier.padding(16.dp)) {
                Text(text = "Тема оформления")
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    typesTheme.forEachIndexed { index, typeTheme ->
                        ElevatedCard(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(16.dp))
                                .clickable {
                                    viewModel.changeTheme(typeTheme = typeTheme)
                                }
                                .border(
                                    2.dp,
                                    if (typeTheme == currentTheme.value) MaterialTheme.colorScheme.primary else Color.Transparent,
                                    RoundedCornerShape(16.dp)
                                )
                                .padding(2.dp)
                            ,

                            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.onSecondary)

                        ) {
                            Icon(
                                modifier = Modifier.padding(16.dp),
                                imageVector = typeTheme.iconMode,
                                contentDescription = null
                            )
                            Text(
                                modifier = Modifier.padding(start = 16.dp, bottom = 12.dp),
                                text = typeTheme.label
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
                Text(text = "Основной цвет приложения")
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)){
                    items(10){
                        Box(modifier = Modifier.size(58.dp).clip(RoundedCornerShape(16.dp)).background(colorList[it]))
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }

}