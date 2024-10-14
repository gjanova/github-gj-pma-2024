package com.example.myapp005moreactivities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp005moreactivities.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.buttonSend.setOnClickListener {
            val data = binding.editTextData.text.toString()
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("EXTRA_DATA", data)
            }
            startActivity(intent)
        }
    }
}
