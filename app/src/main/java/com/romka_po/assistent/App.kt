package com.romka_po.assistent

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.romka_po.assistent.domain.location.LocationService
import com.romka_po.assistent.domain.worker.SyncStaticDataWorker
import dagger.hilt.android.HiltAndroidApp
import ru.mail.maps.sdk.MapGlobalConfig
import ru.mail.maps.sdk.models.MapViewConfig
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltAndroidApp
class App: Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()

        MapGlobalConfig.setMapGlobalConfig(
            MapViewConfig(
                apiKey = "RS62725a01b2f3438f845134ee0d554736edda1e4234c62208271e1a11814d53"
            )
        )

        val updateInterval = 8L // hours

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<SyncStaticDataWorker>(
            updateInterval,
            TimeUnit.HOURS
        ).setConstraints(constraints).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "SyncStaticDataWorker",
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
            workRequest
        )

        val channel = NotificationChannel("location", "location", NotificationManager.IMPORTANCE_DEFAULT)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        Intent(this, LocationService::class.java).apply {
            action = LocationService.ACTION_START
            this@App.startService(this)
        }
    }


}