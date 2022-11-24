package com.example.newcomposeproject.model.data_class_3

import com.google.gson.annotations.SerializedName

data class Data_3(
    val current: Current,
    @SerializedName ("forecast")
    val forecast1: Forecast1,
    val location: Location
)