package com.example.newcomposeproject.model.data_class_3

import com.google.gson.annotations.SerializedName

data class Forecast1(

    @SerializedName("forecastday")
    val forecastday1: List<Forecastday1>
)