package com.romka_po.assistent.ui.screens.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romka_po.assistent.domain.repository.MainRepository
import com.romka_po.assistent.model.network.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val mainRepository: MainRepository
):ViewModel() {
    val emailState = mutableStateOf("")
    val passwordState = mutableStateOf("")
    val loginState = mutableStateOf("")

    fun sendAuthPassword(email:String, password:String) = viewModelScope.launch {
        mainRepository.sendAuthPassword(User(email, password))
    }

    fun sendAuthToken(token:String) = viewModelScope.launch {
        mainRepository.sendAuthToken(token)
    }
}