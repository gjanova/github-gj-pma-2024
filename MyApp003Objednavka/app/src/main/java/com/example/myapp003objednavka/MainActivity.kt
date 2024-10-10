package com.example.myapp003objednavka

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.myapp003objednavka.databinding.ActivityMainBinding




class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        //binding settings
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    title = "Objednávka jídla"

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


    }
}