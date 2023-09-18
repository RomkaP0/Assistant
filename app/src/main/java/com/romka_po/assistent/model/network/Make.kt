package com.romka_po.assistent.model.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Make(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("cyrillic-name") val cyrillicName: String,
    @SerialName("popular") val popular: Boolean,
    @SerialName("country") val country: String,
    @SerialName("models") val models: ArrayList<Models> = arrayListOf(),
    val modelsCount:Int = 0
)