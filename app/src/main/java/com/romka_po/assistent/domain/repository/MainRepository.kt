package com.romka_po.assistent.domain.repository

import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import com.romka_po.assistent.domain.location.LocationClient
import com.romka_po.assistent.model.local.LocalMake
import com.romka_po.assistent.model.local.LocalModel
import com.romka_po.assistent.model.network.User
import com.romka_po.assistent.model.theme.TypeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import ru.mail.maps.data.MapLocation

interface MainRepository {
    val locationFlow: StateFlow<MapLocation>
    fun getTheme(): Flow<TypeTheme>
    fun getUpdatedLocation(
        myLocationClient: LocationClient,
        notification: NotificationCompat.Builder,
        notificationManager: NotificationManager,
        serviceScope: CoroutineScope
    )


    suspend fun changeTheme(typeTheme: TypeTheme)

    suspend fun getMarks(): Flow<List<LocalMake>>
    suspend fun getModelsFromMark(makeId:String): Flow<List<LocalModel>>

    suspend fun sendAuthPassword(user: User)

    suspend fun sendAuthToken(token:String)
    suspend fun searchModels(query: String): Flow<List<LocalModel>>
    fun getDistanceAfter(since:Long):Flow<Double>
}