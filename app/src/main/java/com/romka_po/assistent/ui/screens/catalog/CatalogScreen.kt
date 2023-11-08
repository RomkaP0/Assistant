@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.romka_po.assistent.ui.screens.catalog

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.romka_po.assistent.model.nav.Screens
import com.romka_po.assistent.ui.components.shared.TopWithBottomCard

@Composable
fun CatalogScreen(
    state: BottomSheetScaffoldState,
    height: MutableState<Dp>,
    navController: NavHostController
) {
    val viewModel: CatalogViewModel = hiltViewModel()

    val listCars = viewModel.listCars.collectAsState()

    val listModels = viewModel.listModels.collectAsState()

    val choosed = remember {
        mutableIntStateOf(-1)
    }

    TopWithBottomCard(Modifier, state, height, content = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "Поиск", style = MaterialTheme.typography.displayLarge)
            OutlinedTextField(
                value = "",
                onValueChange = {}
            )
            Spacer(modifier = Modifier.height(0.dp))
        }
    }) {

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp, alignment = Alignment.Top)
        ) {
            itemsIndexed(listCars.value) { position, make ->
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                if (position == choosed.intValue) {
                                    choosed.intValue = -1
                                } else {
                                    choosed.intValue = position
                                }
                                viewModel.getModelsFromMark(make.id)
                            }
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "${make.name} (${make.modelsCount})", style = MaterialTheme.typography.titleLarge)
                        Icon(
                            imageVector = if (position != choosed.intValue) Icons.Default.ArrowDropDown else Icons.Default.ArrowDropUp,
                            contentDescription = null
                        )
                    }

                    if (position == choosed.intValue) {
                        Column(modifier = Modifier) {
                            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                            listModels.value.forEachIndexed { index, model ->
                                Text(
                                    model.name,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                        .clickable {
                                            navController.navigate(Screens.Detail.route)
                                        }
                                        .padding(vertical = 16.dp),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                if (index<listModels.value.size-1)
                                HorizontalDivider(modifier = Modifier.padding(horizontal = 32.dp))
                            }
                        }
                    }
                    HorizontalDivider(modifier = Modifier.fillMaxWidth())
                }

            }
            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}