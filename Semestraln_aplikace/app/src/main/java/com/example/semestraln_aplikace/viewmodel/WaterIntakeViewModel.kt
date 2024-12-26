package com.example.semestraln_aplikace.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.semestraln_aplikace.data.WaterIntake
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class WaterIntakeViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    val allIntakes = mutableStateListOf<WaterIntake>()

    init {
        fetchWaterIntakesFromFirestore()
    }

    fun addWaterIntake(amount: Int) {
        val newIntake = WaterIntake(amount = amount, timestamp = System.currentTimeMillis())
        db.collection("water_intakes").add(newIntake)
            .addOnSuccessListener { documentReference ->
                newIntake.id = documentReference.id
                allIntakes.add(newIntake)
                allIntakes.sortByDescending { it.timestamp }
            }
    }

    private fun fetchWaterIntakesFromFirestore() {
        db.collection("water_intakes")
            .get()
            .addOnSuccessListener { result ->
                allIntakes.clear()
                for (document in result) {
                    val intake = WaterIntake(
                        id = document.id,
                        amount = document.getLong("amount")?.toInt() ?: 0,
                        timestamp = document.getLong("timestamp") ?: 0L
                    )
                    allIntakes.add(intake)
                }
                allIntakes.sortByDescending { it.timestamp }
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }
    }

    fun getDailyIntake(): Map<String, Int> {
        val dailyIntake = mutableMapOf<String, Int>()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        for (intake in allIntakes) {
            val date = dateFormat.format(Date(intake.timestamp))
            dailyIntake[date] = (dailyIntake[date] ?: 0) + intake.amount
        }

        return dailyIntake
    }
}
