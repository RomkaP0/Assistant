package com.romka_po.assistent.ui.screens.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() :ViewModel(){
    private var _selectedCard = MutableStateFlow(0)
    var selectedCard:StateFlow<Int> = _selectedCard.asStateFlow()

    fun pickCard(index:Int){
        _selectedCard.value = index
    }
}