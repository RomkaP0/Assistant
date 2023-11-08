package com.romka_po.assistent.model.network

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email:String = "",
    val password:String = "",
)