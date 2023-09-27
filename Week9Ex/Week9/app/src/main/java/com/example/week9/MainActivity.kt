package com.example.week9

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private lateinit var textView:TextView
    private lateinit var editText: EditText
    private lateinit var applyBtn:Button
    private lateinit var saveBtn:Button
    private lateinit var switch: Switch

    private lateinit var text:String
    private var switchOnOff:Boolean = false

    val SHARED_PREFS = "sharedPrefs"
    val TEXT = "text"
    val SWITCH1 = "switch1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        editText = findViewById(R.id.editText)
        applyBtn = findViewById(R.id.applyBtn)
        saveBtn = findViewById(R.id.saveBtn)
        switch = findViewById(R.id.switchBtn)

        applyBtn.setOnClickListener(View.OnClickListener {
            textView.text = editText.text.toString()
        })

        saveBtn.setOnClickListener(View.OnClickListener {
            saveData()
        })

        loadData()
        updateViews()
    }

    private fun saveData() {
        val sharedPreferences:SharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        val editor:SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(TEXT, textView.text.toString())
        editor.putBoolean(SWITCH1, switch.isChecked)
        editor.apply();
    }

    private fun loadData() {
        val sharedPreferences:SharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        text = sharedPreferences.getString(TEXT, "").toString()
        switchOnOff = sharedPreferences.getBoolean(SWITCH1, false)
    }

    private fun updateViews() {
        textView.text = text
        switch.isChecked = switchOnOff
    }
}