package com.romka_po.assistent.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romka_po.assistent.domain.repository.MainRepository
import com.romka_po.assistent.model.theme.TypeTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
):ViewModel() {

    val currentTheme:StateFlow<TypeTheme> = repository.getTheme().stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TypeTheme.AUTO
    )

    fun getTheme() = repository.getTheme()
}