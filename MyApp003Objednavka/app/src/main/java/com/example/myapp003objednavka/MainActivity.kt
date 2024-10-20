package com.example.myapp003objednavka

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.example.myapp003objednavka.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        //binding settings
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    title = "Objednávka jídla"

        // Zobrazení Toastu při změně vybraného RadioButton
        binding.rgTestoviny.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            val selectedText = selectedRadioButton.text.toString()

            // Vytvoření Toastu
            Toast.makeText(this, "Vybrali jste: $selectedText", Toast.LENGTH_SHORT).show()
        }

    binding.btnObjednat.setOnClickListener{
        //načtení ID vybraného radiobuttonu z radiogroup
        val testovinyRbId = binding.rgTestoviny.checkedRadioButtonId
        val testoviny = findViewById<RadioButton>(testovinyRbId)

        val polevka = binding.cbPolevka.isChecked
        val dezert = binding.cbDezert.isChecked
        val piti = binding.cbPiti.isChecked

        val objednavkaText = "Souhrn objednávky: " +
                "$testoviny.text" +
                (if(polevka)"; polevka" else "") +
                (if(dezert)"; dezert" else "") +
                (if(piti)"; piti" else "")

        binding.tvSouhrn.text = objednavkaText

    }

        //změna obrázku v závislosti na vybraném radiobuttonu

        binding.rbTestoviny1.setOnClickListener{
            binding.ivTestoviny.setImageResource(R.drawable.kremove_rajcatove_testoviny)
        }

        binding.rbTestoviny2.setOnClickListener{
            binding.ivTestoviny.setImageResource(R.drawable.mac_and_cheese)
        }

        binding.rbTestoviny3.setOnClickListener{
            binding.ivTestoviny.setImageResource(R.drawable.zapecene_testoviny_se_smetanou)
        }

        //Zobrazení snackbaru
        binding.btnObjednat.setOnClickListener {
            // Přidáme logiku pro zobrazení Snackbaru
            val snackbar = Snackbar.make(it, "Objednávka byla odeslána!", Snackbar.LENGTH_LONG)

            // Přidáme akci pro zavření Snackbaru
            snackbar.setAction("Zavřít") {
                Toast.makeText(this, "Snackbar zavřen", Toast.LENGTH_SHORT).show()
            }

            // Změna barvy pozadí
            snackbar.setBackgroundTint(resources.getColor(R.color.primary))

            // Zobrazíme Snackbar
            snackbar.show()
        }




    }
}