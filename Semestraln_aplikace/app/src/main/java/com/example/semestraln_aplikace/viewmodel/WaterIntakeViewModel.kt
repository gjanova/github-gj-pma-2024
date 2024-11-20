package com.example.semestraln_aplikace.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.semestraln_aplikace.data.AppDatabase
import com.example.semestraln_aplikace.data.WaterIntake
import kotlinx.coroutines.launch

class WaterIntakeViewModel(application: Application) : AndroidViewModel(application) {

    private val waterIntakeDao = AppDatabase.getDatabase(application).waterIntakeDao()

    fun addWaterIntake(amount: Int) {
        viewModelScope.launch {
            val waterIntake = WaterIntake(amount = amount, timestamp = System.currentTimeMillis())
            waterIntakeDao.insert(waterIntake)
        }
    }

    suspend fun getAllIntakes() = waterIntakeDao.getAllIntakes()
}
