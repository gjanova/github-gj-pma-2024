package com.example.myapp007bfragmentsexample1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp007bfragmentsexample1.PastaFragment  // Ujistěte se, že je import správný

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Přidání PastaFragment do layoutu
        if (savedInstanceState == null) {
            val pastaFragment = PastaFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_list, pastaFragment)
                .commit()
        }
    }

    // Voláno při výběru položky
    fun onBookSelected(title: String, sauce: String, imageResId: Int, description: String) {
        val detailFragment = DetailFragment()
        val bundle = Bundle().apply {
            putString("pasta", title)
            putString("sauce", sauce)
            putInt("imageResId", imageResId)
            putString("description", description)
        }
        detailFragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_detail, detailFragment)
            .commit()
    }
}
