package com.example.myapp007bfragmentsexample1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class DetailFragment : Fragment() {

    private lateinit var textViewPasta: TextView
    private lateinit var textViewSauce: TextView
    private lateinit var imageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment__detail, container, false)
        textViewPasta = view.findViewById(R.id.textViewPasta)
        textViewSauce = view.findViewById(R.id.textViewSauce)
        imageView = view.findViewById(R.id.ivPhoto)

        arguments?.let {
            textViewPasta.text = it.getString("pasta")
            textViewSauce.text = it.getString("sauce")
            imageView.setImageResource(it.getInt("imageResId"))
        }
        return view
    }
}

    fun updateDetails(pasta: String, sauce: String) {
        textViewPasta.text = pasta
        textViewSauce.text = sauce
    }
}
