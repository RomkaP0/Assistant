package com.romka_po.assistent.domain.repository

import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import com.romka_po.assistent.domain.local.DatastoreManager
import com.romka_po.assistent.domain.location.LocationClient
import com.romka_po.assistent.model.LocalLatLon
import com.romka_po.assistent.model.Track
import com.romka_po.assistent.model.local.LocalMake
import com.romka_po.assistent.model.local.LocalModel
import com.romka_po.assistent.model.network.User
import com.romka_po.assistent.model.theme.TypeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import ru.mail.maps.data.MapLocation
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

@Singleton
class MainRepositoryImpl @Inject constructor(
    private val datastoreManager: DatastoreManager,
    private val localDataLayer: LocalDataLayer,
    private val networkDataLayer: NetworkDataLayer
) : MainRepository {

    private var currentTrack: Track? = null

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
                val lat = it.latitude
                val long = it.longitude
                currentTrack = if (currentTrack != null) {
                    val lastKnownPosition = currentTrack!!.positions.last()
                    currentTrack!!.copy(
                        endTimestamp = System.currentTimeMillis(),
                        distance = currentTrack!!.distance + calculateDistance(
                            lastKnownPosition.latitude!!,
                            lastKnownPosition.longitude!!, lat, long
                        ),
                        positions = currentTrack!!.positions + LocalLatLon(lat, long),
                    )
                } else {
                    val now = System.currentTimeMillis()
                    currentTrack = Track(now, now, 0.0, listOf(LocalLatLon(lat, long)))
                    currentTrack
                }
                saveTrack()
                val updateNotification = notification.setContentText("Location: ($lat, $long)")
                notificationManager.notify(1, updateNotification.build())
            }
            .launchIn(serviceScope)
    }

    fun saveTrack() {
        currentTrack?.let { localDataLayer.saveTrack(it) }
//        currentTrack = null
    }

    private fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val R = 6371 // Радиус Земли в километрах

        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)

        val a =
            sin(dLat / 2) * sin(dLat / 2) + cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                    sin(dLon / 2) * sin(dLon / 2)

        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return R * c
    }

    override suspend fun sendAuthToken(token: String) {
        networkDataLayer.sendAuthToken(token)
    }

    override suspend fun sendAuthPassword(user: User) {
        networkDataLayer.sendAuthPassword(user)
    }

    override suspend fun searchModels(query: String) = withContext(Dispatchers.IO) {
        val models = localDataLayer.searchModelLike(query).toMutableList()
        localDataLayer.searchMakeLike(query).forEach {
            models.addAll(localDataLayer.getModelsListFromMake(it.id))
        }

        return@withContext flowOf(models.map {
            it.copy(
                mark = it.mark.lowercase().replaceFirstChar { it.uppercase() }.replace("_", " ")
            )
        }
        )
    }

    override fun getDistanceAfter(since: Long): Flow<Double> =
        localDataLayer.getDistanceAfter(since)
}