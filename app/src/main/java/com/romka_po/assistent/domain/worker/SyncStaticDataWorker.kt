package com.romka_po.assistent.domain.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.romka_po.assistent.domain.repository.LocalDataLayer
import com.romka_po.assistent.domain.repository.NetworkDataLayer
import com.romka_po.assistent.model.network.NetworkMake
import com.romka_po.assistent.model.network.NetworkModel
import com.romka_po.assistent.model.network.toLocalMake
import com.romka_po.assistent.model.network.toLocalModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class SyncStaticDataWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val networkDataLayer: NetworkDataLayer,
    private val localDataLayer: LocalDataLayer
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        var networkDataMake: List<NetworkMake> = emptyList()
        var networkDataModel: List<NetworkModel> = emptyList()
        for (i in 0 until 3) {
            try {
                if (networkDataMake.isEmpty())
                    networkDataMake = networkDataLayer.getMarks()
                if (networkDataModel.isEmpty())
                    networkDataModel = networkDataLayer.getModels()
                if (networkDataMake.isNotEmpty() && networkDataModel.isNotEmpty()) {
                    break
                }
            } catch (e: Exception) {
                e.printStackTrace()
                if (i == 2) {
                    return Result.failure()
                }
            }
        }
        localDataLayer.upsertMakes(networkDataMake.map { it.toLocalMake() })
        localDataLayer.upsertModels(networkDataModel.map { it.toLocalModel() })
        return Result.success()
    }
}