package com.romka_po.assistent.ui.screens.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romka_po.assistent.domain.repository.MainRepository
import com.romka_po.assistent.model.local.LocalMake
import com.romka_po.assistent.model.local.LocalModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private var _listCars: MutableStateFlow<List<LocalMake>> = MutableStateFlow(emptyList())
    val listCars: StateFlow<List<LocalMake>> = _listCars.asStateFlow()

    private var _listModels: MutableStateFlow<List<LocalModel>> = MutableStateFlow(emptyList())
    val listModels: StateFlow<List<LocalModel>> = _listModels.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getMarks().collectLatest {
                _listCars.value = it
            }
        }
    }

    fun getModelsFromMark(markId:String){
        _listModels.value = emptyList()
        viewModelScope.launch {
            repository.getModelsFromMark(markId).collectLatest {
                _listModels.value = it
            }
        }
    }

    init {
//        viewModelScope.launch {
//            carNetworkSource.readFile().collect{
//                _listCars.value = it
//            }
//        }
    }


}