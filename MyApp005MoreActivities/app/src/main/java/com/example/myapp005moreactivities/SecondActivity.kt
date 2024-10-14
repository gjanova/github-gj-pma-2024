package com.example.myapp005moreactivities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp005moreactivities.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val receivedData = intent.getStringExtra("EXTRA_DATA")
        binding.textViewReceived.text = receivedData

        binding.buttonNext.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java).apply {
                putExtra("EXTRA_DATA", receivedData)
            }
            startActivity(intent)
        }
    }
}
