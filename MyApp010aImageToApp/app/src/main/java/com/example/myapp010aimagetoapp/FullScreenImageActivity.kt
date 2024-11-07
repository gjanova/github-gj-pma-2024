package com.example.myapp010aimagetoapp

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp010aimagetoapp.databinding.ActivityFullscreenImageBinding

class FullScreenImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullscreenImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializace viewBinding
        binding = ActivityFullscreenImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Získání URI obrázku z Intentu
        val imageUri = intent.getParcelableExtra<Uri>("image_uri")
        imageUri?.let {
            binding.ivFullScreenImage.setImageURI(it)
        }

        // Zavření náhledu po kliknutí
        binding.ivFullScreenImage.setOnClickListener {
            finish()
        }
    }
}
