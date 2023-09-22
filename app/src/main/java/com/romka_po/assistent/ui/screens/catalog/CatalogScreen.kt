package com.romka_po.assistent.ui.screens.catalog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CatalogScreen() {
    val viewModel: CatalogViewModel = hiltViewModel()

    val listCars = viewModel.listCars.collectAsState()

//    TopWithBottomCard(topContent = {
//        Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.4f)) {
//            TextField(
//                value = "",
//                onValueChange = {}
//            )
//        }
//
//    }) {
//        LazyColumn(
//            verticalArrangement = Arrangement.spacedBy(4.dp, alignment = Alignment.Top)
//        ) {
//            items(listCars.value) { make ->
//                Column {
//                    val state = remember { mutableStateOf(false) }
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .clickable {
//                                state.value = !state.value
//                            }
//                            .padding(16.dp),
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Text(text = "${make.name} (${make.modelsCount})")
//
//                        Icon(
//                            imageVector = if (!state.value) Icons.Default.ArrowDropDown else Icons.Default.ArrowDropUp,
//                            contentDescription = null
//                        )
//                    }
//
//                    if (state.value) {
//                        Column(modifier = Modifier.padding(start = 16.dp)) {
//                            make.models.forEach { model ->
//                                Text(model.name, modifier = Modifier.clickable {
//                                    state.value = true
//                                })
//                            }
//                        }
//                    }
//                    HorizontalDivider(modifier = Modifier.fillMaxWidth())
//                }
//
//            }
//        }
//    }
}