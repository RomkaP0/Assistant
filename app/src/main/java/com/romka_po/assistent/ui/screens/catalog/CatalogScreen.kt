package com.romka_po.assistent.ui.screens.catalog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CatalogScreen() {
    val viewModel:CatalogViewModel = hiltViewModel()

    val listCars = viewModel.listCars.collectAsState()

    val dividerModifier = Modifier.fillMaxWidth(0.9f)

    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
        items(listCars.value){make->
            val state = remember{mutableStateOf(false)}
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(text = "${make.name} (${make.modelsCount})")
                IconButton(onClick = {
                    state.value = !state.value

                }){
                    Icon(imageVector = if (!state.value) Icons.Default.ArrowDropDown else Icons.Default.ArrowDropUp, contentDescription = null)
                }
            }

            if (state.value){
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    make.models.forEach{model->
                        Text(model.name, modifier = Modifier.clickable {
                            state.value = true
                        })
                    }
                }
            }
            HorizontalDivider(modifier = dividerModifier)

        }
    }
}