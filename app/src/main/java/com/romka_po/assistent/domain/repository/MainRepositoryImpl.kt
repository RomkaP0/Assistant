package com.romka_po.assistent.domain.repository

import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import com.romka_po.assistent.domain.local.DatastoreManager
import com.romka_po.assistent.domain.location.LocationClient
import com.romka_po.assistent.model.local.LocalMake
import com.romka_po.assistent.model.local.LocalModel
import com.romka_po.assistent.model.theme.TypeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.mail.maps.data.MapLocation
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl @Inject constructor(
    private val datastoreManager: DatastoreManager,
    private val localDataLayer: LocalDataLayer,
    private val networkDataLayer: NetworkDataLayer
//    private val carNetworkSource: CarNetworkSource
) : MainRepository {

    private val _locationFlow: MutableStateFlow<MapLocation> = MutableStateFlow(MapLocation())
    override val locationFlow: StateFlow<MapLocation> = _locationFlow.asStateFlow()
    override fun getTheme() = datastoreManager.themeSettingsFlow

    override suspend fun changeTheme(typeTheme: TypeTheme) =
        datastoreManager.changeTheme(typeTheme = typeTheme)

    override suspend fun getMarks(): Flow<List<LocalMake>> {
        return localDataLayer.getMakes()
    }

    override suspend fun getModelsFromMark(makeId: String): Flow<List<LocalModel>> {
        return localDataLayer.getModelsFromMake(makeId)
    }

    override fun getUpdatedLocation(
        myLocationClient: LocationClient,
        notification: NotificationCompat.Builder,
        notificationManager: NotificationManager,
        serviceScope: CoroutineScope
    ) {
        myLocationClient.getLocationUpdates(10L)
            .catch { e -> e.printStackTrace() }
            .onEach {
                val mapLocation = MapLocation(
                    latitude = it.latitude,
                    longitude = it.longitude,
                    speed = it.speed,
                    bearing = it.bearing,
                    accuracy = it.accuracy,
                    altitude = it.altitude
                )
                _locationFlow.value = (mapLocation)
                val lat = it.latitude.toString()
                val long = it.longitude.toString()
                val updateNotification = notification.setContentText("Location: ($lat, $long)")
                notificationManager.notify(1, updateNotification.build())
            }
            .launchIn(serviceScope)
    }



}