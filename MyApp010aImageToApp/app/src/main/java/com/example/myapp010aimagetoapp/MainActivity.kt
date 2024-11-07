package com.example.myapp010aimagetoapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp010aimagetoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var uri: Uri? = null  // Globální proměnná pro uložení URI obrázku

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializace pro viewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { selectedUri: Uri? ->
            uri = selectedUri  // Uložení URI obrázku do globální proměnné
            binding.ivImage.setImageURI(uri)
        }

        // Spuštění galerie pro výběr obrázku
        binding.btnTakeImage.setOnClickListener {
            getContent.launch("image/*")
        }

        // Zobrazení obrázku na celé obrazovce při kliknutí na ImageView
        binding.ivImage.setOnClickListener {
            uri?.let {
                val intent = Intent(this, FullScreenImageActivity::class.java)
                intent.putExtra("image_uri", it)  // Předání URI obrázku nové aktivitě
                startActivity(intent)
            }
        }
    }
}
