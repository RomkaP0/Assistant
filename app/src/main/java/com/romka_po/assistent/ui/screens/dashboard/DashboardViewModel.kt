@file:OptIn(ExperimentalMaterial3Api::class)

package com.romka_po.assistent.ui.screens.dashboard

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.mail.maps.data.LocationSource
import ru.mail.maps.data.MapError
import ru.mail.maps.data.MapLocation
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor():ViewModel() {


    val locationSource = object :LocationSource{
        override fun activate() {
        }

        override fun activate(listener: (mapLocation: MapLocation) -> Unit) {
            TODO("Not yet implemented")
        }

        override fun deactivate() {
            TODO("Not yet implemented")
        }

        override fun setMapErrorListener(onMapError: (MapError) -> Unit) {
        }

    }
}