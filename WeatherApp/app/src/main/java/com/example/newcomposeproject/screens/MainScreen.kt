package com.example.newcomposeproject.screens
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.newcomposeproject.R
import com.example.newcomposeproject.model.data_class_2.Forecastday
import com.example.newcomposeproject.view_model.MainViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.PrintFormat


@Preview (showBackground = true)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MessageCard() {

    val viewModel: MainViewModel = viewModel()

    var response : List<String> = viewModel.arrayList.value

    val myColor = Color(0xFF0F4068)
    var town = remember { mutableStateOf("") }
    var speed = remember { mutableStateOf("") }
    var temperature = remember { mutableStateOf("") }
    var date = remember {mutableStateOf ("") }
    var idIcon = remember {mutableStateOf ("") }
    var feelslike_c = remember {mutableStateOf ("") }


    if (response.isNotEmpty()) {
        town.value = response[0]
        speed.value = response[3] + " км/ч"
        temperature.value = response[1] + "°с"
        date.value = response[2]
        idIcon.value = response[4]
        feelslike_c.value = "Ощущается как: " + response[5] + "°с"
    }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(8.dp)
            .alpha(0.6f),
        backgroundColor = myColor,
        elevation = 5.dp

    ) {
        Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(start = 8.dp)) {
            Text(
                text = temperature.value, modifier = Modifier.padding(top = 8.dp),
                fontSize = 35.sp,
                fontFamily = FontFamily(Font(R.font.dosis_extra_light)),
                color = Color.White
            )
            Text(
                text = feelslike_c.value, modifier = Modifier.padding(top = 8.dp),
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.dosis_extra_light)),
                color = Color.White
            )
            Text(
                text = speed.value, modifier = Modifier.padding(top = 8.dp),
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.dosis_extra_light)),
                color = Color.White
            )
            Text(
                text = date.value, modifier = Modifier.padding(top = 8.dp),
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.dosis_extra_light)),
                color = Color.White
            )
            AsyncImage(model = "https:" + idIcon.value, contentDescription = "ic1", modifier = Modifier.size(60.dp))
        }
    }
}




