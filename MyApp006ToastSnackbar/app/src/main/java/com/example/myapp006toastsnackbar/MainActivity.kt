package com.example.myapp006toastsnackbar

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp006toastsnackbar.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializace ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Nastevení akce pro tlačítko
        binding.btnShowToast.setOnClickListener{
            val toast = Toast.makeText(this, "Nazdar - mám hlad", Toast.LENGTH_LONG)

            toast.show()

        }

        // Využití bindingu k tlačítku
        binding.btnShowSnackbar.setOnClickListener {
            // Vytvoření a zobrazení Snackbaru
            val snackbar = Snackbar.make(it, "Toto je Snackbar!", Snackbar.LENGTH_LONG).show()
        }

    }
}