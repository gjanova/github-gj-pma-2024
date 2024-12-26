package com.example.semestraln_aplikace.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "water_intake")
data class WaterIntake(
    @PrimaryKey var id: String = "",
    val amount: Int = 0,
    val timestamp: Long = 0L
)
