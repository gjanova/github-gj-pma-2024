package com.example.semestraln_aplikace.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "water_intake")
data class WaterIntake(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val amount: Int = 0,
    val timestamp: Long = 0L
)
