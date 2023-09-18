@file:OptIn(ExperimentalMaterial3Api::class)

package com.romka_po.assistent.ui.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.romka_po.assistent.R
import com.romka_po.assistent.model.theme.TypeTheme

@ExperimentalMaterial3Api
@Composable
fun SettingsScreen() {
    val typesTheme = TypeTheme.entries.toTypedArray()
    var selectedIndex by remember { mutableStateOf(0) }

    val viewModel:SettingsViewModel = hiltViewModel()


    Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Top)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f)
                .shadow(elevation = 10.dp)
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(100))
                    .border(2.dp, Color.Black.copy(0.1f), RoundedCornerShape(100))
                    .aspectRatio(1f),
                painter = painterResource(id = R.drawable.img008___0004),
                contentDescription = null,
                contentScale = ContentScale.FillHeight
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(modifier = Modifier, text = "Роман Полянский")
                Text(modifier = Modifier, text = "Изменить данные >")
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("")
            SingleChoiceSegmentedButtonRow {
                typesTheme.forEachIndexed { index, typeTheme ->
                    SegmentedButton(
                        onClick = {
                            selectedIndex = index
                            viewModel.changeTheme(typeTheme = typeTheme)
                                  },
                        selected = index == selectedIndex,
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = typesTheme.size
                        )
                    ) {
                        Icon(imageVector = typeTheme.autoMode, contentDescription = null)
                    }
                }
            }
        }
    }
}