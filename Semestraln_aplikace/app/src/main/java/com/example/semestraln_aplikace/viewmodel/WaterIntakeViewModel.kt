package com.example.semestraln_aplikace.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semestraln_aplikace.data.WaterIntake
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WaterIntakeViewModel : ViewModel() {

    private val _intakes = MutableStateFlow<List<WaterIntake>>(emptyList())
    val intakes: StateFlow<List<WaterIntake>> = _intakes

    init {
        fetchWaterIntakesFromFirestore()
    }

    fun addWaterIntake(amount: Int) {
        viewModelScope.launch {
            val db = FirebaseFirestore.getInstance()
            val intake = hashMapOf(
                "amount" to amount,
                "timestamp" to System.currentTimeMillis()
            )

            db.collection("water_intakes")
                .add(intake)
                .addOnSuccessListener {
                    fetchWaterIntakesFromFirestore()
                }
                .addOnFailureListener {
                    // Handle failure
                }
        }
    }

    private fun fetchWaterIntakesFromFirestore() {
        val db = FirebaseFirestore.getInstance()
        db.collection("water_intakes")
            .get()
            .addOnSuccessListener { querySnapshot ->
                val intakes = querySnapshot.toObjects(WaterIntake::class.java)
                _intakes.value = intakes
            }
            .addOnFailureListener {
                // Handle failure
            }
    }

    fun getAllIntakes(): StateFlow<List<WaterIntake>> {
        return intakes
    }
}
