package com.example.myapp001linearlayout

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

        } */

        val etName = findViewById<EditText>(R.id.etName)
        val etLastName = findViewById<EditText>(R.id.etLastName)
        val etPlace = findViewById<EditText>(R.id.etPlace)
        val etVek = findViewById<EditText>(R.id.etVek)
        val tvInfo = findViewById<TextView>(R.id.tvInfo)
        val BtnSend = findViewById<Button>(R.id.BtnSend)
        val BtnDelete = findViewById<Button>(R.id.BtnDelete)

        //Nastavení obsluhy tlačítka Odeslat

        BtnSend.setOnClickListener {
            val name = etName.text.toString()
            val lastname = etLastName.text.toString()
            val place = etPlace.text.toString()
            val vek = etVek.text.toString()

            //Zobrazení textu v TextView

            val formattedText = "Jmenuji se $name $lastname. Moje bydliště je $place. Je mi $vek let."
            tvInfo.text = formattedText
        }

        //Nastevení obsluhy tlačítka Smazat

        BtnDelete.setOnClickListener {
            etName.text.clear()
            etLastName.text.clear()
            etPlace.text.clear()
            etVek.text.clear()

            //Zobrazení textu v TextView

            tvInfo.text = ""

        }

    }
}