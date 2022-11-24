package com.example.newcomposeproject.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.newcomposeproject.R
import com.example.newcomposeproject.model.data_class_2.Forecastday
import com.example.newcomposeproject.model.data_class_3.Forecastday1
import com.example.newcomposeproject.view_model.MainViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat", "CoroutineCreationDuringComposition")
@Preview(showBackground = true)
@Composable
fun trackByDay() {
    val viewModel: MainViewModel = viewModel()
    var response1: ArrayList<ArrayList<Forecastday1>> = viewModel.arrayList2.value


    var date = remember { mutableStateOf("") }
    var picture = remember { mutableStateOf("") }
    var temperature = remember { mutableStateOf("") }
    val myColor = Color(0xFF0F4068)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(8.dp)
            .alpha(0.6f),
        backgroundColor = myColor,
        elevation = 5.dp

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            for (i in 0..5) {
                if (response1.size != 0) {
                    var array = response1[0][i].date.split("-")
                    date.value = array[2] + "-" +  array[1] + "-" + array[0]
                    picture.value = response1[0][i].day.condition.icon
                    temperature.value = "  " + response1[0][i].day.avgtemp_c.toString()
                }
                Row(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = date.value,
                        fontSize = 25.sp,
                        fontFamily = FontFamily(Font(R.font.dosis_extra_light)),
                        color = Color.White
                    )
                    AsyncImage(
                        model = "https:" + picture.value,
                        contentDescription = "ic1",
                        modifier = Modifier
                            .size(60.dp)
                            .padding(bottom = 30.dp)
                    )
                    Text(
                        text = temperature.value,
                        textAlign = TextAlign.Center,
                        fontSize = 25.sp,
                        fontFamily = FontFamily(Font(R.font.dosis_extra_light)),
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(4.dp).background(Color.White))
            }

        }
    }

}