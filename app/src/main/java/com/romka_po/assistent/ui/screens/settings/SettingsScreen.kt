@file:OptIn(ExperimentalMaterial3Api::class)

package com.romka_po.assistent.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.romka_po.assistent.model.theme.TypeTheme
import com.romka_po.assistent.ui.components.shared.TopWithBottomCard

@Composable
fun SettingsScreen(state: BottomSheetScaffoldState, height: MutableState<Dp>) {
    val typesTheme = TypeTheme.entries.toTypedArray()

    val viewModel:SettingsViewModel = hiltViewModel()

    val currentTheme = viewModel.currentTheme.collectAsState()
    TopWithBottomCard(Modifier, state, height, content = {
        Row {
//            Image(painter = painterResource(id = R.drawable.backready), contentDescription = null)
        }
    }) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(
                (-32).dp
            )
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("")
                SingleChoiceSegmentedButtonRow {
                    typesTheme.forEachIndexed { index, typeTheme ->
                        SegmentedButton(
                            onClick = {
                                viewModel.changeTheme(typeTheme = typeTheme)
                            },
                            selected = typeTheme == currentTheme.value,
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
}