package com.example.myapp007bfragmentsexample1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

class PastaFragment : Fragment() {

    private lateinit var listView: ListView
    private val pasta = listOf(
        listOf("Lemon Pasta", "Lemon Sauce", R.drawable.lemon_pasta, "Osvěžující pasta s citronovou kůrou a krémovou omáčkou."),
        listOf("Pasta Primavera", "Primavera Sauce", R.drawable.pasta_primavera, "Jarní pasta plná čerstvé zeleniny."),
        listOf("Chicken and Bacon Pasta", "Creamy Bacon Sauce", R.drawable.chicken_and_bacon_pasta, "Chutná pasta s kuřecím masem a křupavou slaninou.")
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        listView = view.findViewById(R.id.listViewPasta)

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            pasta.map { it.first() }
        )
        listView.adapter = adapter

        // Při kliknutí na položku zavoláme metodu aktivity
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedPasta = pasta[position]
            (activity as? MainActivity)?.onBookSelected(
                selectedPasta[0] as String,  // Název
                selectedPasta[1] as String,  // Omáčka
                selectedPasta[2] as Int,     // ID obrázku
                selectedPasta[3] as String   // Popis
            )
        }
        return view
    }
}