package com.romka_po.assistent.model

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class LatLonListConverter {

    @TypeConverter
    fun fromLatLon(latLonList: List<LocalLatLon>): String {
        return Json.encodeToString(latLonList)
    }

    @TypeConverter
    fun toLatLon(data: String): List<LocalLatLon> {
        return Json.decodeFromString(data)
    }
}