package com.romka_po.assistent

import android.app.Application
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class App:Application() {
    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey("e3778f1f-0637-4a92-9e0c-ba2a22249988")
    }
}