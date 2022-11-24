package com.example.newcomposeproject.model.data_class_3

data class Hour(
    val chance_of_rain: Int,
    val chance_of_snow: Int,
    val condition: Condition,
    val feelslike_c: Double,
    val temp_c: Double,
    val time: String,
    val vis_km: Double,
    val wind_dir: String,
    val wind_kph: Double,

)