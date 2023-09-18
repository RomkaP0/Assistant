package com.romka_po.assistent.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romka_po.assistent.domain.MainRepository
import com.romka_po.assistent.model.theme.TypeTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    fun changeTheme(typeTheme:TypeTheme) = viewModelScope.launch {
        repository.changeTheme(typeTheme)
    }
}