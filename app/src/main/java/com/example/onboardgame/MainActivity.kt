package com.example.onboardgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.onboardgame.ui.theme.OnBoardGameTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnBoardGameTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    OnBoardGame()
                }
            }
        }
    }

    @Composable
    fun OnBoardGame() {

        val treasures = remember { mutableStateOf(0)}

        val direction = remember { mutableStateOf("North")}

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Treasures: ${treasures.value}")
            Text(text = "Direction: ${direction.value}")

            Row() {

                Button(
                    onClick = {
                        direction.value = "Left"
                        if (Random.nextBoolean()) {
                            treasures.value += 1
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Go Left",
                    )
                }

                Button(onClick = {
                    direction.value = "Right"
                    if(Random.nextBoolean()){
                        treasures.value += 1
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Go Right",
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun OnBoardGamePreview(){
        OnBoardGame()
    }
}