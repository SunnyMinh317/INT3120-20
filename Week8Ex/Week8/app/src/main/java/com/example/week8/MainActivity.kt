package com.example.week8

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.provider.Settings
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val mMyBroadcastReceiver = MyBroadcastReceiver()
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.broadcastTest)
        registerReceiver(mMyBroadcastReceiver,
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        )
    }

    private inner class MyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if(intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
                val isTurnedOn = isAirplaneModeOn()
                updateTextView(isTurnedOn)
            }
        }
    }

    private fun isAirplaneModeOn(): Boolean {
        return Settings.Global.getInt(
            contentResolver,
            Settings.Global.AIRPLANE_MODE_ON,
            0
        ) != 0
    }

    private fun updateTextView(isTurnedOn: Boolean) {
        textView.text = "Airplane mode is ${if (isTurnedOn) "on" else "off"}"
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mMyBroadcastReceiver)
    }
}
