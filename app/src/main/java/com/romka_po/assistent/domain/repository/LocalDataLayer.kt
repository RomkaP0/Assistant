package com.romka_po.assistent.domain.repository

import com.romka_po.assistent.domain.local.room.CarMakeDAO
import com.romka_po.assistent.domain.local.room.CarModelDAO
import com.romka_po.assistent.domain.local.room.LocationDAO
import com.romka_po.assistent.model.Track
import com.romka_po.assistent.model.local.LocalMake
import com.romka_po.assistent.model.local.LocalModel
import javax.inject.Inject

class LocalDataLayer @Inject constructor(
    private val carMakeDAO: CarMakeDAO,
    private val carModelDAO: CarModelDAO,
    private val locationDAO: LocationDAO
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

    fun searchMakeLike(query:String) = carMakeDAO.searchLike(query)

    fun searchModelLike(query:String) = carModelDAO.searchLike(query)

    fun getModelsListFromMake(makeId:String) = carModelDAO.getModelsListFromMake(makeId)

    fun saveTrack(track: Track) = locationDAO.upsertLocation(track)

    fun getDistanceAfter(since:Long) = locationDAO.getDistanceAfter(since)
}