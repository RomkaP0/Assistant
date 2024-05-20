package com.romka_po.assistent.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romka_po.assistent.domain.local.DatastoreManager
import com.romka_po.assistent.domain.repository.MainRepository
import com.romka_po.assistent.model.theme.TypeTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    private val datastore: DatastoreManager
) : ViewModel() {
    private val _isFirstOpen: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    private val _currentTheme: MutableStateFlow<TypeTheme?> = MutableStateFlow(null)
    private val _currentColor: MutableStateFlow<Int?> = MutableStateFlow(null)

    val isFirstOpen = _isFirstOpen.asStateFlow()
    val currentTheme = _currentTheme.asStateFlow()
    val currentColor = _currentColor.asStateFlow()

    init {
        viewModelScope.launch {
            datastore.isFirstOpen.collectLatest {
                _isFirstOpen.value = it
            }
        }
        viewModelScope.launch {
            repository.getTheme().collectLatest {
                _currentTheme.value = it
            }
        }

        viewModelScope.launch {
            repository.getColor().collectLatest {
                _currentColor.value = it
            }
        }
    }
}