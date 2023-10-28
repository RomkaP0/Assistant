package com.romka_po.assistent.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalModel(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val mark:String,
    val name: String,
    val cyrillicName: String,
    val type: String,
    val yearFrom:Int,
    val yearTo: Int?
)

