package com.example.myapp02myownlinearlayout

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        val etVysledek = findViewById<EditText>(R.id.etVysledek)
        val btnSend = findViewById<Button>(R.id.btnSend)
        val tvVysledek = findViewById<TextView>(R.id.tvVysledek)

                btnSend.setOnClickListener {
            val vysledek = etVysledek.text.toString()
        }
        val formattedText = "Výsledek je 20."
        tvVysledek.text = formattedText

    }
}