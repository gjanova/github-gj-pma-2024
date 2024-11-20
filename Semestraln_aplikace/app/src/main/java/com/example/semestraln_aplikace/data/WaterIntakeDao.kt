package com.example.semestraln_aplikace.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WaterIntakeDao {
    @Insert
    suspend fun insert(waterIntake: WaterIntake)

    @Query("SELECT * FROM water_intake ORDER BY timestamp DESC")
    suspend fun getAllIntakes(): List<WaterIntake>
}
