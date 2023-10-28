package com.romka_po.assistent.model.network

import com.romka_po.assistent.model.local.LocalMake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkMake(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("cyrillic_name") val cyrillic_name: String,
    @SerialName("popular") val popular: Boolean,
    @SerialName("country") val country: String,
    @SerialName("modelsCount") val modelsCount: Int = 0
)

fun NetworkMake.toLocalMake(): LocalMake {
    return LocalMake(
        id, name, cyrillic_name, popular, country, modelsCount
    )
}