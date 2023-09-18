package com.romka_po.assistent.model.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Models(
    @SerialName("id") var id: String,
    @SerialName("name") var name: String,
    @SerialName("cyrillic-name") var cyrillicName: String,
    @SerialName("class") var type: String,
    @SerialName("year-from") var yearFrom:Int,
    @SerialName("year-to") var yearTo: Int?
)