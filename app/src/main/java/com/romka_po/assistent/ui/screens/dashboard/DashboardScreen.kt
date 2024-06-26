@file:OptIn(ExperimentalMaterial3Api::class)

package com.romka_po.assistent.ui.screens.dashboard

import android.view.LayoutInflater
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.romka_po.assistent.R
import com.romka_po.assistent.model.nav.Screens
import com.romka_po.assistent.ui.components.dashboard.CarSearch
import com.romka_po.assistent.ui.components.shared.HorizontalBottomCard
import com.romka_po.assistent.ui.components.shared.VerticalBottomCard
import com.romka_po.assistent.ui.screens.stats.Periods
import ru.mail.maps.data.LatLon
import ru.mail.maps.data.MapCameraOptions
import ru.mail.maps.data.MapMode
import ru.mail.maps.sdk.Map

private val periods = listOf("День", "Неделя", "Месяц")
var map: Map? = null

@Composable
fun DashboardScreen(navController: NavHostController) {
    val viewModel: DashboardViewModel = hiltViewModel()
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }

    val showSheet = rememberModalBottomSheetState(true)
    val listQueryMakes = viewModel.searchModels.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(
            20.dp, Alignment.Top
        )
    ) {
        ElevatedCard(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Box {
                AndroidView(modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.33f),
                    factory = { context ->
                        val frame =
                            LayoutInflater.from(context).inflate(R.layout.vkmap, null, false)
                        val mapview =
                            frame.findViewById<ru.mail.maps.sdk.views.MapView>(R.id.mapView)
                        mapview.getMapAsync {
                            val locationSource = viewModel.locationSource
                            map = it.apply {
                                setLocationSource(locationSource)
                                setDriveMode(true)
                                setMapFollowMode(MapMode.FollowBearingAndLocation)
                                viewModel._flowLocation.value.bearing?.let { it1 -> setBearing(it1) }
                            }
                        }
                        frame
                    }, update = {
                        it.tag = viewModel._flowLocation.value.bearing.toString()
                        map?.flyTo(
                            LatLon(
                                viewModel._flowLocation.value.latitude,
                                viewModel._flowLocation.value.longitude
                            ), true,
                            1000,
                            cameraOptions = MapCameraOptions(
                                viewModel._flowLocation.value.bearing,
                                16f
                            )
                        )
                    })
            }
        }
        HorizontalBottomCard(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clickable {
                },
            color = MaterialTheme.colorScheme.tertiaryContainer
        ) {
            Icon(
                imageVector = Icons.Default.WorkspacePremium,
                contentDescription = null
            )
            Text(
                modifier = Modifier,
                text = "Приобрести Премиум - доступ",
                style = MaterialTheme.typography.titleMedium
            )
        }

        Row(
            modifier = Modifier.padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            VerticalBottomCard(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Статус", style = MaterialTheme.typography.headlineMedium)
                Text(text = "Поездка")

            }
            VerticalBottomCard(
                modifier = Modifier.weight(1f),
                clickFunc = { navController.navigate(Screens.Health.route) }
            ) {
                Text(text = "Хорошее", style = MaterialTheme.typography.headlineMedium)
                Text(text = "Состояние")
            }

        }

            VerticalBottomCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                clickFunc = {
                    openBottomSheet = true
                }
            ) {
                Text(text = "Лада Гранта", style = MaterialTheme.typography.headlineMedium)
                Text(text = "Личный автомобиль")
            }
            VerticalBottomCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                clickFunc = { navController.navigate(Screens.Chart.route) },
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f),
                    ) {
//                        Text(text = viewModel.distance.doubleValue.toString(), style = MaterialTheme.typography.headlineMedium, maxLines = 1)
                        Text(text = "368.4 км", style = MaterialTheme.typography.headlineMedium, maxLines = 1)
                        Text(text = "Преодолено за март")
                    }

                    Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        VerticalDivider(modifier = Modifier.height(60.dp), thickness = 2.dp)
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.End,
                        ) {
                            periods.forEachIndexed { index, s ->
                                TextButton(
                                    modifier = Modifier
                                        .height(height = 20.dp)
                                        .padding(0.dp),
                                    onClick = {
                                        viewModel.currentPeriod = index
                                        viewModel.changePeriod(Periods.entries[index])
                                    },
                                    contentPadding = PaddingValues(1.dp),
                                    colors = if (viewModel.currentPeriod == index) ButtonDefaults.textButtonColors(
                                        containerColor = MaterialTheme.colorScheme.primaryContainer
                                    ) else ButtonDefaults.textButtonColors()
                                ) {
                                    Text(
                                        text = s,
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            color = if (index == 0) MaterialTheme.colorScheme.primary else Color.Unspecified
                                        )
                                    )
                                }

                            }
                        }
                    }
                }
            }
    }
    if (openBottomSheet)
        ModalBottomSheet(sheetState = showSheet, onDismissRequest = {
            openBottomSheet = false
            viewModel._searchModels.value = emptyList()
        }) {
            val text = remember { mutableStateOf("") }
            CarSearch(text, listQueryMakes) { query -> viewModel.searchModels(query) }
            Spacer(modifier = Modifier.height(40.dp))
        }
}
