package com.example.newcomposeproject.view_model

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.newcomposeproject.model.RetrofitHelper
import com.example.newcomposeproject.model.data_class.Data
import com.example.newcomposeproject.model.data_class_2.Data_2
import com.example.newcomposeproject.model.data_class_2.Forecastday
import com.example.newcomposeproject.model.data_class_3.Data_3
import com.example.newcomposeproject.model.data_class_3.Forecastday1
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var arrayList : MutableState<List<String>> = mutableStateOf(listOf())
    @SuppressLint("MutableCollectionMutableState")
    var arrayList1 : MutableState<ArrayList<ArrayList<Forecastday>>> = mutableStateOf(arrayListOf())
    @SuppressLint("MutableCollectionMutableState")
    var arrayList2 : MutableState<ArrayList<ArrayList<Forecastday1>>> = mutableStateOf(arrayListOf())



    suspend fun doResponse(countryName : String) = coroutineScope {
        launch {
            val call: Call<Data> = RetrofitHelper.getInstance().getQuotes(countryName)
            call.enqueue(object : Callback<Data?> {
                override fun onResponse(call: Call<Data?>, response: Response<Data?>) {
                   var list : ArrayList<String> = ArrayList ()
                    list.add(response.body()?.location?.name.toString())
                    list.add(response.body()?.current?.temp_c.toString())
                    list.add(response.body()?.location?.localtime.toString())
                    list.add(response.body()?.current?.vis_km.toString())
                    list.add(response.body()?.current?.condition?.icon.toString())
                    list.add(response.body()?.current?.feelslike_c.toString())

                    arrayList.value = list
                }

                override fun onFailure(call: Call<Data?>, t: Throwable) {
                    Toast.makeText(getApplication(), "Fail to get the data..", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }
    }
    suspend fun doResponse_2(countryName : String) = coroutineScope {
        launch {
            val call: Call<Data_2> = RetrofitHelper.getInstance().getQuotes_2(countryName)
            call.enqueue(object : Callback<Data_2?> {
                override fun onResponse(call: Call<Data_2?>, response: Response<Data_2?>) {
                    var list = ArrayList <ArrayList<Forecastday>>()
                    response.body()?.forecast?.forecastday?.let { list.add(it) }

                    arrayList1.value = list
                }

                override fun onFailure(call: Call<Data_2?>, t: Throwable) {
                    Toast.makeText(getApplication(), "Fail to get the data..", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }
    }
    suspend fun doResponse_3(countryName : String) = coroutineScope  {
        launch {
            val call: Call<Data_3> = RetrofitHelper.getInstance().getQuotes_3(countryName)
            call.enqueue(object : Callback<Data_3?> {
                override fun onResponse(call: Call<Data_3?>, response: Response<Data_3?>) {
                    var list = ArrayList <ArrayList<Forecastday1>>()
                    response.body()?.forecast1?.forecastday1?.let { list.add(it as ArrayList<Forecastday1>) }


                    arrayList2.value = list
                }

                override fun onFailure(call: Call<Data_3?>, t: Throwable) {
                    Toast.makeText(getApplication(), "Fail to get the data..", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }
    }

}