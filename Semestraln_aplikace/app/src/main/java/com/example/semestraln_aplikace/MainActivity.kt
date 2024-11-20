package com.example.semestraln_aplikace

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.semestraln_aplikace.viewmodel.WaterIntakeViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val waterIntakeViewModel: WaterIntakeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val amountEditText: EditText = findViewById(R.id.amountEditText)
        val addButton: Button = findViewById(R.id.addButton)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        addButton.setOnClickListener {
            val amount = amountEditText.text.toString().toInt()
            waterIntakeViewModel.addWaterIntake(amount)
            Toast.makeText(this, "Water intake added", Toast.LENGTH_SHORT).show()
        }

        lifecycleScope.launch {
            val allIntakes = waterIntakeViewModel.getAllIntakes()
            recyclerView.adapter = WaterIntakeAdapter(allIntakes)
        }
    }
}
