package com.romka_po.assistent.domain.local.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.romka_po.assistent.model.local.LocalModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CarModelDAO {
    @Upsert
    fun upsertModels(modelsList:List<LocalModel>)

    @Query("Select * from LocalModel Where mark=:makeId")
    fun getModelsFromMake(makeId:String):Flow<List<LocalModel>>

    @Query("Select * from LocalModel where id = :modelId")
    fun getModelWithId(modelId:String):LocalModel
}