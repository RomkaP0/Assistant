@file:OptIn(ExperimentalMaterial3Api::class)

package com.romka_po.assistent.ui.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.DirectionsCarFilled
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.romka_po.assistent.R
import com.romka_po.assistent.model.theme.TypeTheme
import com.romka_po.assistent.ui.components.account.ColumnLine
import com.romka_po.assistent.ui.theme.blueColorScheme
import com.romka_po.assistent.ui.theme.brownSchemePair
import com.romka_po.assistent.ui.theme.redColorScheme

@Composable
fun SettingsScreen(navController: NavHostController) {
    val typesTheme = TypeTheme.entries.toTypedArray()

    val viewModel: SettingsViewModel = hiltViewModel()

    val currentTheme by viewModel.currentTheme.collectAsState()

    val currentColorPosition by viewModel.currentColorPosition.collectAsState()

    var isSheetOpen by remember { mutableStateOf(false) }

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Image(
            modifier = Modifier
                .padding(top = 14.dp, bottom = 12.dp)
                .fillMaxWidth(0.4f)
                .aspectRatio(1f)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(100.dp))
                .background(Color.Blue),
            painter = painterResource(id = R.drawable.aaa),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Roman Polyanskiy",
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 28.8.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF212121),
            )
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp),
            text = "roman.kradyk@gmail.com",

            )
        HorizontalDivider(modifier = Modifier.padding(vertical = 24.dp))
        ColumnLine(text = "Редактировать профиль", icon = Icons.Outlined.PersonOutline, false) {

        }
        ColumnLine(text = "VAZ Granta", icon = Icons.Outlined.DirectionsCarFilled, false) {

        }

        ColumnLine(text = "Нотификация", icon = Icons.Outlined.Notifications, false) {

        }
        ColumnLine(text = "Безопасность", icon = Icons.Outlined.Shield, false) {

        }
        ColumnLine(text = "Оформление", icon = Icons.Outlined.DarkMode, red = false) {
            isSheetOpen = true
        }
        ColumnLine(text = "Выйти из профиля", icon = Icons.AutoMirrored.Outlined.Logout, true) {
        }
    }
    if (isSheetOpen) {
        ModalBottomSheet(onDismissRequest = { isSheetOpen = false }, tonalElevation = 0.dp) {
            Column(Modifier.padding(16.dp)) {
                Text(text = "Тема оформления")
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    typesTheme.forEach { typeTheme ->
                        ElevatedCard(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(16.dp))
                                .clickable {
                                    viewModel.changeTheme(typeTheme = typeTheme)
                                }
                                .border(
                                    4.dp,
                                    if (typeTheme == currentTheme) MaterialTheme.colorScheme.primary else Color.Transparent,
                                    RoundedCornerShape(16.dp)
                                )
                                .padding(4.dp),
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
                LazyRow(modifier = Modifier.fillMaxWidth().height(100.dp), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.Bottom){
                    items(com.romka_po.assistent.ui.theme.Color.entries.toTypedArray()){
                        val colorScheme = when(it) {
                            com.romka_po.assistent.ui.theme.Color.BROWN -> brownSchemePair
                            com.romka_po.assistent.ui.theme.Color.BLUE -> blueColorScheme
                            com.romka_po.assistent.ui.theme.Color.RED -> redColorScheme
                        }
                        Box(
                            modifier = Modifier
                                .size(90.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(colorScheme.first.primary.copy(if (currentColorPosition == it.ordinal) 1f else 0.4f))
                                .clickable { viewModel.changeColorPosition(it.ordinal) }
                            )
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }

}