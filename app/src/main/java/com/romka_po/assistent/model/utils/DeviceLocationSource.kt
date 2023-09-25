package com.romka_po.assistent.model.utils

import ru.mail.maps.data.LocationSource
import ru.mail.maps.data.MapError
import ru.mail.maps.data.MapLocation

class DeviceLocationSource:LocationSource {
    override fun activate() {
        TODO("Not yet implemented")
    }

    override fun activate(listener: (mapLocation: MapLocation) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun deactivate() {
        TODO("Not yet implemented")
    }

    override fun setMapErrorListener(onMapError: (MapError) -> Unit) {
        TODO("Not yet implemented")
    }
}