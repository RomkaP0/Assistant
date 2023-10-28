package com.romka_po.assistent.domain.repository

import com.romka_po.assistent.domain.api.BackendAPI
import javax.inject.Inject

class NetworkDataLayer @Inject constructor(
    private val backendAPI: BackendAPI
)
{
    suspend fun getMarks()=backendAPI.getAllMarks()
    suspend fun getModels() = backendAPI.getAllModels()
    suspend fun getModelsFromMark(makeId:String) = backendAPI.getModelsFromMark(makeId)
}