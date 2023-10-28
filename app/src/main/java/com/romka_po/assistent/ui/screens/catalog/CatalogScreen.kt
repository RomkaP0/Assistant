@file:OptIn(ExperimentalMaterial3Api::class)

package com.romka_po.assistent.ui.screens.catalog

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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
                .fillMaxHeight(0.4f)
        ) {
            Text(text = "Поиск автомобиля по названию")
            TextField(
                value = "",
                onValueChange = {}
            )
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
                                if (position == choosed.intValue){
                                    choosed.intValue = -1
                                }
                                else{
                                    choosed.intValue = position
                                }
                                    viewModel.getModelsFromMark(make.id)
                            }
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "${make.name} (${make.modelsCount})")
                        Icon(
                            imageVector = if (position != choosed.intValue) Icons.Default.ArrowDropDown else Icons.Default.ArrowDropUp,
                            contentDescription = null
                        )
                    }

                    if (position == choosed.intValue) {
                        Column(modifier = Modifier.padding(start = 16.dp)) {
                            listModels.value.forEach { model ->
                                Text(model.name, modifier = Modifier.clickable {
                                    navController.navigate(Screens.Detail.route)
                                })
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