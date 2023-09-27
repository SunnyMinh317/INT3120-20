package com.example.week9_internal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    val FILE_NAME: String = "file.txt"
    val EX_FILE_NAME: String = "exFile.txt"
    private lateinit var saveBtn:Button
    private lateinit var loadBtn:Button


    lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editText)
//        saveBtn = findViewById(R.id.saveBtn)
//        loadBtn = findViewById(R.id.loadBtn)


    }

    fun save(v: View) {
        var text:String = editText.text.toString()
        var fos: FileOutputStream? = null;

        fos = openFileOutput(FILE_NAME, MODE_PRIVATE)
        fos.write(text.toByteArray())
        Toast.makeText(this, "Saved to $filesDir/$FILE_NAME",
            Toast.LENGTH_LONG).show();
    }

    fun saveEx(v: View) {
        val text: String = editText.text.toString()

        val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val file = File(downloadsDir, EX_FILE_NAME)

        var fos: FileOutputStream? = null

        try {
            fos = FileOutputStream(file)
            fos.write(text.toByteArray())
            Toast.makeText(this, "Saved to ${file.absolutePath}", Toast.LENGTH_LONG).show()
        } catch (e: IOException) {
            e.printStackTrace()
            // Handle exception
        } finally {
            fos?.close()
        }
    }


    fun load(v: View) {
        var fis:FileInputStream = openFileInput(FILE_NAME)
        var isr:InputStreamReader = InputStreamReader(fis)
        var br:BufferedReader = BufferedReader(isr)
        var sb:StringBuilder =  StringBuilder()
        var text:String?

        while (true) {
            text = br.readLine()
            if (text != null) {
                sb.append(text).append("\n")
            } else {
                break
            }
        }

        editText.text = Editable.Factory.getInstance().newEditable(sb.toString())
    }

    fun loadEx(v: View) {
        val downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val file = File(downloadPath, EX_FILE_NAME)
        var fis:FileInputStream = FileInputStream(file)
        var isr:InputStreamReader = InputStreamReader(fis)
        var br:BufferedReader = BufferedReader(isr)
        var sb:StringBuilder =  StringBuilder()
        var text:String?

        while (true) {
            text = br.readLine()
            if (text != null) {
                sb.append(text).append("\n")
            } else {
                break
            }
        }
        editText.text = Editable.Factory.getInstance().newEditable(sb.toString())
    }
}