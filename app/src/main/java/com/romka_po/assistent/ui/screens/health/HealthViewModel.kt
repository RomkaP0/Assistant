package com.romka_po.assistent.ui.screens.health

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class HealthViewModel @Inject constructor(): ViewModel() {

    private val dialogState = MutableStateFlow<HealthData?>(null)
//
//    private val mutableActionState = MutableStateFlow<Action?>(null)
//    val actionState = mutableActionState.asSharedFlow()

    val viewState = combine(
        dialogState,
        dialogState,
        HealthViewState::valueOf
    )

    class HealthViewState(
        val healthData: HealthData?
    ) {


        companion object {

            fun valueOf(
                healthData: HealthData?,
                healthData1: HealthData?
            ): HealthViewState {
                return HealthViewState(healthData)
            }
        }
    }


    fun onComponentClicked(data: HealthData) {
        dialogState.value = data
    }

    fun onComponentChanged(data: HealthData) {

    }

    fun onDialogClose() {
        dialogState.value = null
    }
}