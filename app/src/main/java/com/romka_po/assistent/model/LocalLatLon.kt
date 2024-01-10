package com.romka_po.assistent.model

import kotlinx.serialization.Serializable
import ru.mail.maps.data.LatLon

@Serializable
data class LocalLatLon(
    val latitude: Double?,
    val longitude: Double?
)

fun LatLon.toLocalLatLong(): LocalLatLon {
    return LocalLatLon(
        latitude,
        longitude
    )
}