package com.romka_po.assistent.domain.location

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.huawei.hms.location.ActivityIdentification
import com.huawei.hms.location.ActivityIdentificationService
import com.romka_po.assistent.R
import com.romka_po.assistent.domain.identificate.LocationBroadcastReceiver
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

    private var pendingIntent: PendingIntent? = null
    private var activityIdentificationService: ActivityIdentificationService? = null



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
        activityIdentificationService = ActivityIdentification.getService(this)
// Obtain a PendingIntent object.
        pendingIntent = getPendingIntent()

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> start()
            ACTION_STOP -> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {

        activityIdentificationService!!.createActivityIdentificationUpdates(
            5000,
            pendingIntent
        )
            // Define callback for request success.
            .addOnSuccessListener {
                // TODO: Define callback for success in requesting activity identification updates.
            }
            // Define callback for request failure.
            .addOnFailureListener { e ->
                // TODO: Define callback for failure in requesting activity identification updates.
            }
        val notification = NotificationCompat.Builder(this, "location")
            .setContentTitle("Tracking Location")
            .setContentText("Location: null")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setOngoing(true)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        Log.i("repk", "start: ${repository}")
        repository.getUpdatedLocation(
            myLocationClient, notification, notificationManager, serviceScope
        )

        startForeground(1, notification.build())
    }

    private fun stop() {
        activityIdentificationService!!.deleteActivityIdentificationUpdates(pendingIntent)
            // Define callback for success in stopping requesting activity identification updates.
            .addOnSuccessListener {
                // TODO: Define callback for success in stopping requesting activity identification updates.

            } // Define callback for failure in stopping requesting activity identification updates.
            .addOnFailureListener { e ->
                // TODO: Define callback for success in stopping requesting activity identification updates.

            }
        stopForeground(true)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }
    // Obtain PendingIntent associated with the custom static broadcast class LocationBroadcastReceiver.
    private fun getPendingIntent(): PendingIntent? {
        val intent = Intent(this, LocationBroadcastReceiver::class.java)
        intent.action = LocationBroadcastReceiver.ACTION_PROCESS_LOCATION
        return if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R) {
            PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        } else {
            // For devices running Android 12 or later, you need to configure the mutability of pendingIntent. By default, the configuration is PendingIntent.FLAG_MUTABLE.
            // If compileSdkVersion is 30 or earlier, you can replace PendingIntent.FLAG_MUTABLE with 1<<25.
            // PendingIntent.FLAG_MUTABLE can be used only when buildToolsVersion is 31 or later.
            PendingIntent.getBroadcast(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            )
        }
    }

    companion object {
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
    }
}