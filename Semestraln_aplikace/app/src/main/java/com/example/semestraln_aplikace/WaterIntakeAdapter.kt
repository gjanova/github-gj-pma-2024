package com.example.semestraln_aplikace

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.semestraln_aplikace.data.WaterIntake
import java.text.SimpleDateFormat
import java.util.Locale

class WaterIntakeAdapter(private val waterIntakes: List<WaterIntake>) :
    RecyclerView.Adapter<WaterIntakeAdapter.WaterIntakeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaterIntakeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.water_intake_item, parent, false)
        return WaterIntakeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WaterIntakeViewHolder, position: Int) {
        val current = waterIntakes[position]
        holder.amountTextView.text = "${current.amount} ml"
        holder.timestampTextView.text = formatTimestamp(current.timestamp)
    }

    override fun getItemCount() = waterIntakes.size

    private fun formatTimestamp(timestamp: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        return sdf.format(timestamp)
    }

    class WaterIntakeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val amountTextView: TextView = itemView.findViewById(R.id.amountTextView)
        val timestampTextView: TextView = itemView.findViewById(R.id.timestampTextView)
    }
}
