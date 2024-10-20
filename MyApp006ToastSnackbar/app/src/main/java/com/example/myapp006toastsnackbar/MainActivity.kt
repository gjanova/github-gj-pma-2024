package com.example.myapp006toastsnackbar

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp006toastsnackbar.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     // Inicializace ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       /* // Nastavení akce pro tlačítko Toast
        binding.btnShowToast.setOnClickListener {
            Toast.makeText(this, "Nazdar - mám hlad", Toast.LENGTH_LONG).show()
        }*/


        // Nastavení akce pro tlačítko Toast
        binding.btnShowToast.setOnClickListener {
            showCustomToast("Nazdar - mám hlad")
        }
    }

    // Funkce pro vlastní Toast s ikonkou
    private fun showCustomToast(message: String) {
        // Nafouknutí vlastního layoutu pro Toast
        val inflater = LayoutInflater.from(this)
        val layout = inflater.inflate(R.layout.custom_toast, null)

        // Nastavení textu a ikony
        val toastMessage: TextView = layout.findViewById(R.id.toast_message)
        val toastIcon: ImageView = layout.findViewById(R.id.toast_icon)
        toastMessage.text = message
        toastIcon.setImageResource(R.drawable.ikona) // Použití ikony z drawable

        // Vytvoření a zobrazení Toastu
        val toast = Toast(this)
        toast.duration = Toast.LENGTH_LONG
        toast.view = layout
        toast.show()



        // Nastavení akce pro tlačítko Snackbar
        binding.btnShowSnackbar.setOnClickListener {
            // Vytvoření Snackbaru
            val snackbar = Snackbar.make(it, "Toto je Snackbar!", Snackbar.LENGTH_LONG)

            // Nastavení délky zobrazení
            snackbar.setDuration(7000)
            snackbar.setBackgroundTint((Color.parseColor("#FFFFFF")))


            // Přidání akce
            snackbar.setAction("Zavřít") {
                Toast.makeText(this, "Zavírám Snackbar", Toast.LENGTH_LONG).show()
            }

            // Zobrazení Snackbaru
            snackbar.show()

        }
    }


    }
