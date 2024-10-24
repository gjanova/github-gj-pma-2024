package com.example.myapp007bfragmentsexample1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class DetailFragment : Fragment() {

    private lateinit var textViewPasta: TextView
    private lateinit var textViewSauce: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment__deatil, container, false)
        textViewPasta = view.findViewById(R.id.textViewPasta)
        textViewSauce = view.findViewById(R.id.textViewSauce)

        // Načtení argumentů a aktualizace textových polí
        arguments?.let {
            val pasta = it.getString("pasta")
            val sauce = it.getString("sauce")
            updateDetails(pasta ?: "Unknown", sauce ?: "Unknown")
        }

        return view
    }

    // Metoda pro aktualizaci zobrazení detailů
    fun updateDetails(title: String, author: String) {
        textViewPasta.text = pasta
        textViewSauce.text = sauce
    }
}