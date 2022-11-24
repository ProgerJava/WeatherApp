package com.example.newcomposeproject.model


import com.example.newcomposeproject.model.data_class.Data
import com.example.newcomposeproject.model.data_class_2.Data_2
import com.example.newcomposeproject.model.data_class_3.Data_3
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("current.json?key=c2eefb72f61b41e5a1c114053221911&aqi=no")
    fun getQuotes(@Query("q") country: String) : Call<Data>

    @GET("forecast.json?key=c2eefb72f61b41e5a1c114053221911&days=1&aqi=no&alerts=no")
    fun getQuotes_2(@Query("q") country: String) : Call<Data_2>

    @GET("forecast.json?key=c2eefb72f61b41e5a1c114053221911&days=6&aqi=no&alerts=no")
    fun getQuotes_3(@Query("q") country: String) : Call<Data_3>


}