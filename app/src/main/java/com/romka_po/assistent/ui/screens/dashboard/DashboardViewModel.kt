@file:OptIn(ExperimentalMaterial3Api::class)

package com.romka_po.assistent.ui.screens.dashboard

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romka_po.assistent.domain.repository.MainRepository
import com.romka_po.assistent.model.local.LocalModel
import com.romka_po.assistent.ui.screens.stats.Periods
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toKotlinLocalDate
import ru.mail.maps.data.LocationSource
import ru.mail.maps.data.MapError
import ru.mail.maps.data.MapLocation
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    val _flowLocation = mutableStateOf(MapLocation())

    val _searchModels: MutableStateFlow<List<LocalModel>> = MutableStateFlow(emptyList())
    val searchModels: StateFlow<List<LocalModel>> = _searchModels.asStateFlow()

    val distance = mutableDoubleStateOf(0.0)
    var currentPeriod by mutableIntStateOf(0)

    var periodJob: Job? = null

    init {
        sendNewPeriodDistance(Periods.DAY)
    }

    val locationSource = object : LocationSource {
        override fun activate() {

        }

        override fun activate(listener: (mapLocation: MapLocation) -> Unit) {
            viewModelScope.launch {
                repository.locationFlow.collectLatest {
                    Log.i("VDV", ": $it")
                    _flowLocation.value = it
                    listener(it)
                }
            }
        }

        override fun deactivate() {
        }

        override fun setMapErrorListener(onMapError: (MapError) -> Unit) {
        }

    }

    fun searchModels(query: String) = viewModelScope.launch {
        repository.searchModels(query).collect {
            _searchModels.value = it
            Log.i("queeeery", it.toString())
        }
    }

    private fun sendNewPeriodDistance(period: Periods) {
        val nowInstant = Clock.System.now()
        val now = nowInstant.toEpochMilliseconds()
        val periodInMillis = when (period) {
            Periods.DAY -> 86400000L
            Periods.WEEK -> 604800000L
            Periods.MONTH -> {
                val date = LocalDate.now(ZoneId.systemDefault()).minusMonths(1)
                val days = date.toKotlinLocalDate().atStartOfDayIn(TimeZone.currentSystemDefault())
                    .daysUntil(nowInstant, TimeZone.currentSystemDefault())
                println(days)
                days * 86400000L
            }
            else -> 1
        }
        println(now)
        val before = now + 86400000 - now % 86400000 - periodInMillis
        periodJob = viewModelScope.launch {
            repository.getDistanceAfter(before).collectLatest {
                distance.doubleValue = it
            }
        }
    }

    fun changePeriod(period: Periods) {
        periodJob?.cancel()
        sendNewPeriodDistance(period)
    }
}