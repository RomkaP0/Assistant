package com.romka_po.assistent.domain.repository

import com.romka_po.assistent.domain.local.room.CarMakeDAO
import com.romka_po.assistent.domain.local.room.CarModelDAO
import com.romka_po.assistent.model.local.LocalMake
import com.romka_po.assistent.model.local.LocalModel
import javax.inject.Inject

class LocalDataLayer @Inject constructor(
    private val carMakeDAO: CarMakeDAO,
    private val carModelDAO: CarModelDAO
) {
    fun upsertMakes(makesList:List<LocalMake>){
        carMakeDAO.upsertMakes(makesList)
    }

    fun upsertModels(modelsList: List<LocalModel>){
        carModelDAO.upsertModels(modelsList)
    }

    fun getMakes() = carMakeDAO.getMakes()

    fun getModelsFromMake(makeId:String) = carModelDAO.getModelsFromMake(makeId)

    fun getMarkWithId(modelId:String) = carModelDAO.getModelWithId(modelId)

}