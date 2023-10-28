package com.romka_po.assistent.domain.api

import com.romka_po.assistent.model.network.NetworkMake
import com.romka_po.assistent.model.network.NetworkModel
import retrofit2.http.GET
import retrofit2.http.Path

interface BackendAPI {

    @GET("marks")
    suspend fun getAllMarks():List<NetworkMake>

    @GET("models")
    suspend fun getAllModels():List<NetworkModel>

    @GET("models/{id}")
    suspend fun getModelsFromMark(
        @Path("id") markId:String
    ):List<NetworkModel>
}