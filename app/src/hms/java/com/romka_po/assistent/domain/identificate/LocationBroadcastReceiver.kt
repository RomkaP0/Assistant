package com.romka_po.assistent.domain.identificate

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.huawei.hms.location.ActivityIdentificationResponse

class LocationBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            val action = intent.action
            if (ACTION_PROCESS_LOCATION == action) {
                // Obtain ActivityIdentificationResponse and extract a detected activity status list from it.
                val activityIdentificationResponse =
                    ActivityIdentificationResponse.getDataFromIntent(intent)
                val list =
                    activityIdentificationResponse.activityIdentificationDatas
                // TODO: Process the obtained activity status list.
            }
        }
    }

    companion object {
        // Activity identification service broadcast action.
        const val ACTION_PROCESS_LOCATION =
            "com.huawei.hms.location.ACTION_PROCESS_LOCATION"
    }
}