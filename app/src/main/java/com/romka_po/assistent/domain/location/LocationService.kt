package com.romka_po.assistent.domain.location

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.romka_po.assistent.R
import com.romka_po.assistent.domain.repository.MainRepositoryImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import javax.inject.Inject

@AndroidEntryPoint
class LocationService: Service(
) {
    @Inject
    lateinit var repository: MainRepositoryImpl


    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private lateinit var myLocationClient: LocationClient
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        myLocationClient = MyDefaultLocationClient(
            applicationContext,
        )
        // Obtain an activityIdentificationService instance.
// Obtain a PendingIntent object.

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> start()
            ACTION_STOP -> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {

        val notification = NotificationCompat.Builder(this, "location")
            .setContentTitle("Запись местоположения")
            .setContentText("Расстояние: null")
            .setSmallIcon(R.drawable.bar_chart_fill0_wght400_grad0_opsz48)
            .setOngoing(true)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        repository.getUpdatedLocation(
            myLocationClient, notification, notificationManager, serviceScope
        )

        startForeground(1, notification.build())
    }

    private fun stop() {
        repository.saveTrack()
        stopForeground(true)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }


    companion object {
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
    }
}