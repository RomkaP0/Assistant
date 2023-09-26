package com.romka_po.assistent

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import ru.mail.maps.sdk.MapGlobalConfig
import ru.mail.maps.sdk.models.MapViewConfig

@HiltAndroidApp
class App:Application() {
    override fun onCreate() {
        super.onCreate()

        MapGlobalConfig.setMapGlobalConfig(
            MapViewConfig(
                apiKey = "RS62725a01b2f3438f845134ee0d554736edda1e4234c62208271e1a11814d53"
            )
        )
        val channel = NotificationChannel("location", "location", NotificationManager.IMPORTANCE_DEFAULT)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}