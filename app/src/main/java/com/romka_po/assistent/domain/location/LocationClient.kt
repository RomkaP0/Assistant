package com.romka_po.assistent.domain.location

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationClient {
    fun getLocationUpdates(interval: Long): Flow<Location>

    class AnyException(message: String): Exception()
}