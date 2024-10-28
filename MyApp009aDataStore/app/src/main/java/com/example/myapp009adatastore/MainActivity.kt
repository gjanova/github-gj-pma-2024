package com.example.myapp009adatastore

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val dataStoreViewModel: DataStoreViewModel by viewModels()

    private lateinit var editTextName: EditText
    private lateinit var editTextAge: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonLoad: Button
    private lateinit var textViewUsername: TextView
    private lateinit var textViewAge: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextName = findViewById(R.id.editTextName)
        editTextAge = findViewById(R.id.editTextAge)
        buttonSave = findViewById(R.id.buttonSave)
        buttonLoad = findViewById(R.id.buttonLoad)
        textViewUsername = findViewById(R.id.textViewUsername)
        textViewAge = findViewById(R.id.textViewAge)

        buttonSave.setOnClickListener {
            val name = editTextName.text.toString()
            val age = editTextAge.text.toString()
            dataStoreViewModel.saveData(name, age)
        }

        buttonLoad.setOnClickListener {
            loadData()
        }
    }

    private fun loadData() {
        lifecycleScope.launch {
            val userData = dataStoreViewModel.readData().first()
            textViewUsername.text = "Jméno: ${userData.first}"
            textViewAge.text = "Věk: ${userData.second}"
        }
    }
}
