@file:OptIn(ExperimentalMaterial3Api::class)

package com.romka_po.assistent.ui.screens.dashboard

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romka_po.assistent.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.mail.maps.data.LocationSource
import ru.mail.maps.data.MapError
import ru.mail.maps.data.MapLocation
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    var location = MapLocation()

    init {
//        Log.i("repke", "start: $repository}")
//        viewModelScope.launch {
//            repository.locationFlow.collectLatest {
//                Log.i("VDV", ": $it")
//                location = it
//            }
//        }
    }

    val locationSource = object : LocationSource {
        override fun activate() {

        }

        override fun activate(listener: (mapLocation: MapLocation) -> Unit) {
            viewModelScope.launch {
                repository.locationFlow.collectLatest {
                    Log.i("VDV", ": $it")
                    listener(it)
                }
            }
        }

        override fun deactivate() {
        }

        override fun setMapErrorListener(onMapError: (MapError) -> Unit) {
        }

    }
}