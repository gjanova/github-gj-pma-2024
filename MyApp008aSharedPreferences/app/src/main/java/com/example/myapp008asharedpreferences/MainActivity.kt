package com.example.myapp008asharedpreferences

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var textViewUsername: TextView
    private lateinit var textViewAge: TextView
    private lateinit var editTextName: EditText
    private lateinit var editTextAge: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonLoad: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializace komponentů
        textViewUsername = findViewById(R.id.textViewUsername)
        textViewAge = findViewById(R.id.textViewAge)
        editTextName = findViewById(R.id.editTextName)
        editTextAge = findViewById(R.id.editTextAge)
        buttonSave = findViewById(R.id.buttonSave)
        buttonLoad = findViewById(R.id.buttonLoad)

        // Nastavení posluchače pro tlačítko Uložit
        buttonSave.setOnClickListener {
            val name = editTextName.text.toString()
            val age = editTextAge.text.toString().toIntOrNull() ?: 0
            saveUserData(name, age)
        }

        // Nastavení posluchače pro tlačítko Načíst
        buttonLoad.setOnClickListener {
            loadUserData()
        }
    }

    // Uložení dat do SharedPreferences
    private fun saveUserData(name: String, age: Int) {
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("username", name)
        editor.putInt("age", age)
        editor.apply() // Uložení změn
    }

    // Načtení dat ze SharedPreferences
    private fun loadUserData() {
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "Není nastaveno") // výchozí hodnota
        val age = sharedPreferences.getInt("age", 0) // výchozí hodnota

        // Zobrazit načtené údaje
        textViewUsername.text = username
        textViewAge.text = age.toString()
    }

    // Smazání dat (pokud byste potřebovali)
    private fun clearUserData() {
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear() // Smaže všechna data
        editor.apply()
    }
}
