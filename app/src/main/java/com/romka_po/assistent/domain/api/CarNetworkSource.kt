package com.romka_po.assistent.domain.api

import android.content.Context
import com.romka_po.assistent.model.network.Make
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader

class CarNetworkSource(private val context: Context) {

    suspend fun readFile() = flow {
        val makes = mutableListOf<Make>()
        CoroutineScope(Dispatchers.IO).async {
            val string = try {
                val file = context.assets.open("cars.json")
                val bufferedReader = BufferedReader(InputStreamReader(file))
                val stringBuilder = StringBuilder()
                bufferedReader.useLines { lines ->
                    lines.forEach {
                        stringBuilder.append(it)
                    }
                }
                stringBuilder.toString()
            } catch (e: Exception) {
                e.printStackTrace()
                ""
            }
            Json.decodeFromString<List<Make>>(string).forEach {
                val count = it.models.size
                makes.add(it.copy(modelsCount = count))
            }
        }.await()
        emit(makes.toList())
    }

}