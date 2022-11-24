package com.example.newcomposeproject.model.data_class

data class Current(
    val cloud: Int,
    val condition: Condition,
    val feelslike_c: Double,
    val is_day: Int,
    val temp_c: Double,
    val vis_km: Double,
)