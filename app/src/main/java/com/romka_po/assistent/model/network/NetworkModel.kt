package com.romka_po.assistent.model.network

import com.romka_po.assistent.model.local.LocalModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkModel(
    @SerialName("id") val id: String,
    @SerialName("mark") val mark:String,
    @SerialName("name") val name: String,
    @SerialName("cyrillic_name") val cyrillic_name: String,
    @SerialName("myclass") val myclass: String,
    @SerialName("year_from") val year_from:Int,
    @SerialName("year_to") val year_to: Int?
)

fun NetworkModel.toLocalModel(): LocalModel {
    return LocalModel(
        id, mark, name, cyrillic_name, myclass, year_from, year_to
    )
}