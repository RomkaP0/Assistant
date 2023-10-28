package com.romka_po.assistent.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalMake(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val cyrillicName: String,
    val popular: Boolean,
    val country: String,
    val modelsCount:Int = 0
)

