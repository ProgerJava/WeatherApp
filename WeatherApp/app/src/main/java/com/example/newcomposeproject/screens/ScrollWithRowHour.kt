package com.example.newcomposeproject.screens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
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
import com.example.newcomposeproject.view_model.MainViewModel

@Preview(showBackground = true)
@Composable
fun trackByHour() {
    val viewModel: MainViewModel = viewModel()
    var response: ArrayList<ArrayList<Forecastday>> = viewModel.arrayList1.value


    var time = remember { mutableStateOf("") }
    var picture = remember { mutableStateOf("") }
    var temperature = remember { mutableStateOf("") }
    val myColor = Color(0xFF0F4068)

    LazyRow(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)

    ) {
        items((0..23).toList()) {
            if (response.size != 0) {
                time.value = response[0][0].hour[it].time.substring(10, 16)
                picture.value = response[0][0].hour[it].condition.icon
                temperature.value = "  " + response[0][0].hour[it].temp_c.toString()
            }
            Row(
                modifier = Modifier
                    .width(70.dp)
                    .alpha(0.6f)
                    .clip(shape = RoundedCornerShape(4.dp))
                    .background(myColor),
                horizontalArrangement = Arrangement.Center

            ) {
                Column() {
                    Text(
                        text = time.value,
                        fontSize = 25.sp,
                        fontFamily = FontFamily(Font(R.font.dosis_extra_light)),
                        color = Color.White
                    )
                    AsyncImage(
                        model = "https:" + picture.value,
                        contentDescription = "ic1",
                        modifier = Modifier.size(60.dp)
                    )
                    Text(
                        text = temperature.value, modifier = Modifier.padding(top = 4.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 25.sp,
                        fontFamily = FontFamily(Font(R.font.dosis_extra_light)),
                        color = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}