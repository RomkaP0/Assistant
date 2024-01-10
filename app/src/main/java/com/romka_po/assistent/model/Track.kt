package com.romka_po.assistent.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Track(
    @PrimaryKey
    val startTimestamp: Long,
    val endTimestamp: Long,
    val distance: Double,
    val positions: List<LocalLatLon> = emptyList()
)