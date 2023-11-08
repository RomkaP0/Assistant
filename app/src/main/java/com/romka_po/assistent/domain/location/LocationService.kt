package com.romka_po.assistent.domain.location

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.ActivityRecognition
import com.google.android.gms.location.ActivityRecognitionClient
import com.romka_po.assistent.R
import com.romka_po.assistent.domain.identificate.ActivityTransitionReceiver
import com.romka_po.assistent.domain.identificate.ActivityTransitionsUtil
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

    lateinit var client: ActivityRecognitionClient



    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private lateinit var myLocationClient: LocationClient
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        client = ActivityRecognition.getClient(this)

        myLocationClient = MyDefaultLocationClient(
            applicationContext,
        )
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
            .setContentTitle("Tracking Location")
            .setContentText("Location: null")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setOngoing(true)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        Log.i("repk", "start: ${repository}")
        requestForUpdates()
        repository.getUpdatedLocation(
            myLocationClient, notification, notificationManager, serviceScope
        )

        startForeground(1, notification.build())
    }

    private fun stop() {
        stopForeground(true)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    @SuppressLint("MissingPermission")
    private fun requestForUpdates() {
        client
            .requestActivityTransitionUpdates(
                ActivityTransitionsUtil.getActivityTransitionRequest(),
                getPendingIntent()
            )
            .addOnSuccessListener {
                Log.i("requestForUpdates: ", "successful registration")
            }
            .addOnFailureListener {
                Log.i("requestForUpdates: ", "Unsuccessful registration")
            }
    }

    private fun getPendingIntent(): PendingIntent {
        val intent = Intent(this, ActivityTransitionReceiver::class.java)
        return PendingIntent.getBroadcast(
            this,
            122,
            intent,
            PendingIntent.FLAG_MUTABLE
        )
    }

    companion object {
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
    }
}