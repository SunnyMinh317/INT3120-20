package com.example.week8

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            val isTurnedOn = isAirplaneModeOn(context)
            val updateIntent = Intent("com.example.week8.UPDATE_TEXT")
            updateIntent.putExtra("isTurnedOn", isTurnedOn)
            context?.sendBroadcast(updateIntent)
        }
    }

    private fun isAirplaneModeOn(context: Context?): Boolean {
        return Settings.Global.getInt(
            context?.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON,
            0
        ) != 0
    }
}
