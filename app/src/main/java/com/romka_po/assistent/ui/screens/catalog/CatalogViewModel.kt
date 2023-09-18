package com.romka_po.assistent.ui.screens.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romka_po.assistent.domain.api.CarNetworkSource
import com.romka_po.assistent.model.network.Make
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val carNetworkSource: CarNetworkSource
) : ViewModel() {

    private var _listCars: MutableStateFlow<List<Make>> = MutableStateFlow(emptyList())
    val listCars: StateFlow<List<Make>> = _listCars.asStateFlow()

    init {
        viewModelScope.launch {
            carNetworkSource.readFile().collect{
                _listCars.value = it
            }
        }
    }


}