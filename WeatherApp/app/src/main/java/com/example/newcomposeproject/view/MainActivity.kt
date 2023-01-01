package com.example.newcomposeproject.view



import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newcomposeproject.R
import com.example.newcomposeproject.screens.MessageCard
import com.example.newcomposeproject.screens.trackByDay
import com.example.newcomposeproject.screens.trackByHour

import com.example.newcomposeproject.view_model.MainViewModel
import kotlinx.coroutines.*
import java.util.*


class MainActivity : ComponentActivity() {


    @SuppressLint("CoroutineCreationDuringComposition", "CommitPrefEdits")
    @OptIn(DelicateCoroutinesApi::class, ExperimentalComposeUiApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var mainViewModel : MainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        var sharedPreferences : SharedPreferences = getSharedPreferences("Country", MODE_PRIVATE)
        var sharedPreferencesEditor : SharedPreferences.Editor = sharedPreferences.edit()

        GlobalScope.launch(Dispatchers.Default) {
            mainViewModel.doResponse(sharedPreferences.getString("Country", "Москва").toString())
            mainViewModel.doResponse_2(sharedPreferences.getString("Country", "Москва").toString())
            mainViewModel.doResponse_3(sharedPreferences.getString("Country", "Москва").toString())
        }

        setContent {
            val flag = remember { mutableStateOf(false) }
            val keyboardController = LocalSoftwareKeyboardController.current
            val textFieldColor = Color(0xFF0F4068)
            val viewModel: MainViewModel = viewModel()


            Image(
                painter = painterResource(id = R.drawable.background_main),
                contentDescription = "im1",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column {
                Row(horizontalArrangement = Arrangement.Center) {
                    var text = remember { mutableStateOf(sharedPreferences.getString("Country", "Москва").toString()) }
                    TextField(
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            textDecoration = TextDecoration.None
                        ),
                        value = text.value,
                        singleLine = true,
                        enabled = flag.value,
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White,
                            backgroundColor = textFieldColor,
                            cursorColor = Color.White,
                            focusedIndicatorColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .clickable { flag.value = !flag.value }
                            .height(60.dp)
                            .fillMaxWidth()
                            .alpha(0.7f)
                            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
                        leadingIcon = { Icon(Icons.Default.Place, contentDescription = "Место") },
                        onValueChange = { newText ->
                            text.value = newText
                        },
                        keyboardActions = KeyboardActions(
                            onDone = {
                                GlobalScope.launch() {
                                    viewModel.doResponse(text.value)
                                    viewModel.doResponse_2(text.value)
                                    mainViewModel.doResponse_3(text.value)
                                    sharedPreferencesEditor.putString("Country", text.value).apply()
                                }
                                flag.value = false
                                keyboardController?.hide()
                            })
                    )

                }
                MessageCard()
                trackByHour()
                trackByDay()
            }
        }

    }

    override fun onPostResume() {
        super.onPostResume()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

    }

}


